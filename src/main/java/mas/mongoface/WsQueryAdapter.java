package mas.mongoface;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

public class WsQueryAdapter extends WebSocketAdapter {
    @Override
    public void onWebSocketConnect(Session sess) {
        System.out.println(sess.getRemote());
    }
}
