import com.mongodb.MongoClient;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.UnwindOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Updates.*;
import static com.mongodb.client.model.Accumulators.*;


public class Application {
    private MongoCollection<Document> collectionShop;
    private MongoCollection<Document> collectionProduct;


    public Application() {

    }

    void init() {
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        MongoDatabase database = mongoClient.getDatabase("local");


        collectionShop = database.getCollection("Shop");
        collectionProduct = database.getCollection("Product");

        collectionShop.drop();
        collectionProduct.drop();
    }

    void addShop(String commandNewShop) {
        String[] commands = commandNewShop.split(" ");
        if (commands.length != 2) {
            System.out.println("Неправильный ввод магазина");
        } else {
            List<String> products = new ArrayList<>();
            Document document = new Document().append("name", commands[1]).append("products", products);
            collectionShop.insertOne(document);
        }

    }

    void addProduct(String commandNewProduct) {
        String[] commands = commandNewProduct.split(" ");
        if (commands.length != 3) {
            System.out.println("Неправильный ввод товара");
        } else {
            Document document = new Document().append("name", commands[1]).append("price", Integer.valueOf(commands[2]));
            collectionProduct.insertOne(document);
        }
    }

    void displayProduct(String commandDisplayProduct) {
        String[] commands = commandDisplayProduct.split(" ");
        if (commands.length != 3) {
            System.out.println("Wrong command");
        } else {
            Document documentPr = collectionProduct.find(eq("name", commands[1])).first();
            Document found = collectionShop.find(eq("name", commands[2])).first();
            if (documentPr != null && found != null) {
                List<String> productList = (List<String>) found.get("products");
                productList.add(documentPr.getString("name"));
                collectionShop.updateOne(collectionShop.find(eq("name", commands[2])).first(),
                        set("products", found.get("products")));
                Document document = collectionShop.find(eq("name", commands[2])).first();
                System.out.println("Товар " + documentPr.get("name") + " выставлен в магазине " + document.get("name"));
            } else {
                System.out.println("Нет такого товара или магазина");
            }
        }

    }

    void productStatistics() {
        collectionShop.aggregate(
                Arrays.asList(
                        lookup("Product", "products", "name", "prod_name"),
                        unwind("$prod_name"),
                        group("$name", sum("count", 1),
                                Accumulators.max("maxPrice", "$prod_name.price"),
                                Accumulators.min("minPrice", "$prod_name.price"),
                                Accumulators.avg("averagePrice", "$prod_name.price")
                        )
                )
        ).forEach((Consumer<Document>) doc -> {
            System.out.println("Название магазина: " + doc.get("_id"));
            System.out.println("Общее количество товаров: " + doc.get("count"));
            System.out.println("Средняя цена товаров: " + doc.get("averagePrice"));
            System.out.println("Самый дорогой товар: " + doc.get("maxPrice"));
            System.out.println("Самый дешёвый товар: " + doc.get("minPrice"));
            //System.out.println("Количество товаров дешевле 100 рублей: " + doc.get("countLess100"));
            //System.out.println(doc);
            System.out.println(" ");
        });

        collectionShop.aggregate(
                Arrays.asList(
                        lookup("Product", "products", "name", "prod_name"),
                        unwind("$prod_name"),
                        match(lt("prod_name.price", 100)),
                        group("$name", sum("countLess100", 1)
                        )
                )
        ).forEach((Consumer<Document>) doc -> {
                System.out.println("Количество товаров дешевле 100 рублей: " + doc.get("countLess100"));
        });

    }


}
