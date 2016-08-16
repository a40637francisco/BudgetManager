package javaa.tests;

import javaa.main.model.dataAPI.model.DtoItem;
import javaa.main.model.domain.Budget;
import javaa.main.model.domain.Item;
import javaa.main.model.domain.ParserToDomain;
import javaa.main.model.domain.Section;
import javaa.main.util.WriteXMLFile;

import static javaa.main.util.ReadXMLFile.readBudgetXML;
import static javaa.main.util.ReadXMLFile.readItemsXML;
import static javaa.main.util.WriteXMLFile.writeBudgetXML;


/**
 * Created on 14/08/2016.
 */
public class Tests {


    public static void main(String[] args) {

        //testWriteBudget();
        //testReadBudget("laa");
        //testReadItems();
        testWriteItemToItems();
    }

    private static void testWriteItemToItems() {
        Item item1 = new Item();
        item1.setValue(100);
        item1.setDescription("janelas");

        WriteXMLFile.writeItemToItemsXML(item1);
    }

    private static void testWriteBudget() {
        Budget b = new Budget();
        b.setName("laa");
        b.setAddress("rua do coco");

        Section section = new Section();
        section.setDescription("cozinha");
        section.setValue(300);

        Item item1 = new Item();
        item1.setValue(10);
        item1.setDescription("rebouco");

        section.addItem(item1);
        b.addItem(section);

        writeBudgetXML(b);
    }

    private static void testReadBudget(String name) {
        readBudgetXML(name, (object) -> {
            System.out.println(object);
        });
    }

    private static void testReadItems() {

        readItemsXML((i)->{

            for(DtoItem is: i) {
                System.out.println(ParserToDomain.parseDtoItem(is));
            }
        });
    }
}
