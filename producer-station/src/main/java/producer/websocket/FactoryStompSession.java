package producer.websocket;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public final class FactoryStompSession {

    private static StompSession instance;

    private FactoryStompSession() {
    }

    public static StompSession getInstance(WebSocketStompClient webSocketStompClient, String urlServeurWebsocket) throws ExecutionException, InterruptedException {
        if (Objects.isNull(instance)) {
            StompSessionHandler sessionHandler = new CustomStompSessionHandler();
            instance = webSocketStompClient.connect(urlServeurWebsocket, sessionHandler).get();
            return instance;
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
