/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import com.mongodb.*;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author m_elieba
 */
public class MongoCRUD {

    public static List<ProductComment> comments = new ArrayList<ProductComment>();
    public static MongoClient mongoClient;
    public static MongoCollection<ProductComment> collection;
    public static Date date = Calendar.getInstance().getTime();
    public static MongoCursor<ProductComment> cursor;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
    public static String strDate = dateFormat.format(date);

    public static void connect() {
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase db = mongoClient.getDatabase("inv").withCodecRegistry(pojoCodecRegistry);
        collection = db.getCollection("comments", ProductComment.class);
    }

    public static void insert(ProductComment comment) {
        collection.insertOne(comment);
    }

    public static void update(BasicDBObject query) {
        ProductComment newComment = new ProductComment();
        newComment.setCommentId("7");
        newComment.setUserId(9);
        newComment.setProductId(10);
        newComment.setTimer(strDate);
        newComment.setComment("I hate this product");
        collection.findOneAndReplace(query, newComment);

    }

    public static String retreiveAll() {
        cursor = collection.find().projection(Projections.fields(Projections.include("comment"), Projections.excludeId())).iterator();
//            cursor = collection.find().iterator();

        try {
            while (cursor.hasNext()) {
                return (cursor.next().getComment());
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public static List<ProductComment> retreiveOnePoductComments(BasicDBObject query) {
        cursor = collection.find(query).iterator();
        while (cursor.hasNext()) {
        comments.add(cursor.next());
        }

        return comments;
    }

    public static void remove() {
        BasicDBObject query = new BasicDBObject("commentId", "7");
        collection.findOneAndDelete(query);
    }

}
