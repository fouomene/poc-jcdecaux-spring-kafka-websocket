package producer.controler;


import events.StationEvent;
import messages.JcdecauxMessage;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import producer.dto.ParameterDTO;
import producer.websocket.FactoryStompSession;

import javax.validation.Valid;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Controller
public class ProducerController {

    private static final Logger log = LoggerFactory.getLogger(ProducerController.class);

    public static final String PRODUCER_TEMPLATE = "start";

    @Autowired
    public NewTopic stationsJcdecauxTopic;

    @Value("${jcdecaux.api.key}")
    private String apiKeyJcdecaux;

    @Value("${jcdecaux.api.url}")
    private String urlApiJcdecaux;

    @Autowired
    private WebSocketStompClient webSocketStompClient;

    @Value("${url.serveur.websocket}")
    private String urlServeurWebsocket;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    KafkaTemplate<String, StationEvent> stationKafkaTemplate;

    @GetMapping("/start")
    public String root(Model model) {

        model.addAttribute("parameter", new ParameterDTO(10000, 3));
        return PRODUCER_TEMPLATE; // cf. resources/templates/start.html
    }

    @PostMapping("/start")
    public String start(@Valid ParameterDTO parameter, BindingResult bindingResult, Model model) throws ExecutionException, InterruptedException {

        FactoryStompSession.getInstance(webSocketStompClient, urlServeurWebsocket)
                .send("/app/jcdecauxproducer", new JcdecauxMessage("Démarrage du Producer JCDecaux avec les parametres : SleepTime=" + parameter.getSleepTime() + " NumberOfCall=" + parameter.getNumberOfCall()));

        StationEvent[] stations;
        for (int j = 0; j < parameter.getNumberOfCall(); j++) {

            stations = restTemplate.getForObject(urlApiJcdecaux + apiKeyJcdecaux, StationEvent[].class);

            log.info("Nbre de stations JCDecaux récuperer et envoyer au Consumer = " + Objects.requireNonNull(stations).length);

            FactoryStompSession.getInstance(webSocketStompClient, urlServeurWebsocket)
                    .send("/app/jcdecauxproducer", new JcdecauxMessage("Nbre de stations JCDecaux récuperer et envoyer au Consumer = " + Objects.requireNonNull(stations).length));

            for (StationEvent station : stations) {
                stationKafkaTemplate.send(stationsJcdecauxTopic.name(), station);
            }

            try {
                Thread.sleep(parameter.getSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        model.addAttribute("parameter", parameter);
        //model.addAttribute("message", "Producer run End...");

        FactoryStompSession.getInstance(webSocketStompClient, urlServeurWebsocket)
                .send("/app/jcdecauxproducer", new JcdecauxMessage("Arrêt du Producer JCDecaux ..."));


        return PRODUCER_TEMPLATE; // cf. resources/templates/start.html
    }
}
