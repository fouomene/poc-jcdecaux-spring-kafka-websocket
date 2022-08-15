package producer.websocket;

import messages.JcdecauxMessage;
import messages.StationMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class StationController {

    @MessageMapping("/jcdecauxproducer")
    @SendTo("/topic/stations")
    public StationMessage station(JcdecauxMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new StationMessage(HtmlUtils.htmlEscape(message.getMessage()));
    }

}
