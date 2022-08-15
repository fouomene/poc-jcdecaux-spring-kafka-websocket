package consumer.listener;

import events.StationEvent;
import messages.JcdecauxMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import consumer.websocket.FactoryStompSession;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class StationKafkaListeners {

    private static final Logger log = LoggerFactory.getLogger(StationKafkaListeners.class);

    private static final Map<String, StationEvent> mapOfStationEvent = new HashMap<>();

    @Autowired
    private WebSocketStompClient webSocketStompClient;

    @Value("${url.serveur.websocket}")
    private String urlServeurWebsocket;

    @KafkaListener(
            topics = "stationsjcdecaux",
            groupId = "afrinnov2",
            containerFactory = "stationFactory"
    )
    void listener(StationEvent station) throws ExecutionException, InterruptedException {

        // log.info(station.toString());

        String name = station.getName() + station.getContractName();
        int available_bike_stands = station.getAvailableBikeStands();
        if (!mapOfStationEvent.containsKey(name)) {
            mapOfStationEvent.put(name, station);
        }

        StationEvent city_station = mapOfStationEvent.get(name);
        int count_diff = available_bike_stands - city_station.getAvailableBikeStands();
        if (count_diff != 0) {
            mapOfStationEvent.put(name, station);
            if (count_diff > 0) {

                log.info(count_diff + " Support(s) à vélos disponibles ou occupés *** STATION : " + station.getContractName() + " **** ADRESSE : " + station.getAddress());

                FactoryStompSession.getInstance(webSocketStompClient, urlServeurWebsocket)
                        .send("/app/jcdecaux", new JcdecauxMessage(count_diff + " SV *** STATION : " + station.getContractName() + " *** ADRESSE : " + station.getAddress()));
            }
        }
    }
}
