package mas.mongoface;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Mongoface {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8000);
        ServletContextHandler wsHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        wsHandler.setContextPath("/");
        wsHandler.addServlet(WsQueryHandler.class, "/query");
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.addHandler(wsHandler);
        server.setHandler(handlerCollection);
        server.start();
        server.join();
    }
}