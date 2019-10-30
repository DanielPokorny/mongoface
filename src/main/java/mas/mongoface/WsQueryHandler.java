package mas.mongoface;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WsQueryHandler extends WebSocketServlet {
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(WsQueryAdapter.class);
    }
}
