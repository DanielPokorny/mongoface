package mas.mongoface;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mongodb.MongoClient;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.FileReader;

public class Mongoface {
    public static MongoFaceConfig config;

    public static void main(String[] args) throws Exception {
        String iniFile = args[0];

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(iniFile));
        config = gson.fromJson(reader, MongoFaceConfig.class);

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );


        Server server = new Server(config.port);
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