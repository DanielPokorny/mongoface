package mas.mongoface;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Mongoface {
    public static void main(String[] args) {
        Server server = new Server(8000);
        ServletContextHandler wsHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
    }
}