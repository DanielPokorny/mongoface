package mas.mongoface;

import com.mongodb.client.*;
import org.bson.Document;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;

import java.io.IOException;

import static mas.mongoface.Mongoface.config;

public class WsQueryAdapter extends WebSocketAdapter {
    private Session session;

    @Override
    public void onWebSocketConnect(Session sess) {
        session = sess;
        System.out.println(sess.getRemote());
    }

    @Override
    public void onWebSocketText(String message) {
        MongoClient mongoClient = MongoClients.create(config.mongoURL);
        MongoDatabase database = mongoClient.getDatabase(config.mongoDB);
        MongoCollection<Document> collection = database.getCollection(config.mongoCollection);

        MongoCursor<Document> cursor = collection.find().cursor();
        while(cursor.hasNext()) {
            String text = cursor.next().toJson();
            try {
                session.getRemote().sendString(text);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        cursor.close();
    }
}
