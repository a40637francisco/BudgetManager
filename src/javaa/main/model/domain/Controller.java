package javaa.main.model.domain;

import javaa.main.model.dataAPI.model.DtoItem;
import javaa.main.util.ReadXMLFile;
import javaa.main.util.WriteXMLFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created on 16/08/2016.
 */
public class Controller {

    public static void getAllProducts(Consumer<Item> callback) {

       ReadXMLFile.readItemsXML((allItems)-> {
           for(DtoItem dto: allItems) {
               callback.accept(ParserToDomain.parseDtoItem(dto));
           }
       });
    }

    public static void addToProducts(Item item) {
        WriteXMLFile.writeItemToItemsXML(item);
    }

    public static Stream<Budget> getAllBudgets() {
        Queue<CompletableFuture<Item>> listt = new ConcurrentLinkedQueue<CompletableFuture<Item>>();
        listt.add(CompletableFuture.supplyAsync(()->{

            return null;
        }));
        ArrayList<Budget> list = new ArrayList<>();

        File f = new File("documents");
        Path p = Paths.get(f.toURI());
        try {
            Files.walk(p).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {

                    ReadXMLFile.readBudgetXML(filePath.getFileName().toString(), (bud->{
                        list.add(ParserToDomain.parseDtoBudget(bud));
                    }));
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("pasta não existe?");
        }

        for(CompletableFuture<Item> promise: listt) {
            //callback.accept(promise.join());
        }
        return list.stream();
    }


}
