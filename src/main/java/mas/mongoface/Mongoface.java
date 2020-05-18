package mas.mongoface;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;

public class Mongoface {
    public static MongoFaceConfig config;

    public static void main(String[] args) throws Exception {
        String iniFile = args[0];

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(iniFile));
        config = gson.fromJson(reader, MongoFaceConfig.class);

        Server server = new Server(config.port);
        ServletContextHandler wsHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHandler httpHandler = new ServletHandler();
        httpHandler.addServletWithMapping(BlockingServlet.class, "/status");
        httpHandler.addServletWithMapping(WsQueryHandler.class, "/query");

/*        wsHandler.setContextPath("/");
        wsHandler.addServlet(WsQueryHandler.class, "/query");
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.addHandler(wsHandler);
        handlerCollection.addHandler(httpHandler);
        server.setHandler(handlerCollection);*/
        server.setHandler(httpHandler);
        server.start();
        server.join();

    }


}