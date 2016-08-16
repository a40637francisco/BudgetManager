package javaa.main.util;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javaa.main.model.dataAPI.model.DtoItem;
import javaa.main.model.domain.Budget;
import javaa.main.model.domain.Item;
import javaa.main.model.domain.Items;
import javaa.main.model.domain.Section;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created on 14/08/2016.
 */
public class WriteXMLFile {

    public static void writeItemToItemsXML(Item item) {
        try {
            File fXmlFile = new File("items/items.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            ArrayList<DtoItem> list = new ArrayList<>();
            NodeList nList = doc.getElementsByTagName("items");

            if(nList.item(0) != null) {
                Element elem = (Element) nList.item(0);
                addItem(item, elem, doc);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(fXmlFile);
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeBudgetXML(Budget budget) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("budget");
            Attr attr = doc.createAttribute("name");
            attr.setValue(budget.getName());
            rootElement.setAttributeNode(attr);
            doc.appendChild(rootElement);

            Element address = doc.createElement("address");
            address.appendChild(doc.createTextNode(budget.getAddress()));
            rootElement.appendChild(address);

            Element items = doc.createElement("items");
            rootElement.appendChild(items);

            addItems(budget, items, doc);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("documents/" + budget.getName() + ".xml"));
            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addItems(Budget budget, Element rootElement, Document doc) {
        if (budget.getItems().size() > 0) {
            for(Items item: budget.getItems()) {
                if (item instanceof Item) {
                    addItem((Item) item, rootElement, doc);
                } else if (item instanceof Section) {
                    addSection((Section) item, rootElement, doc);
                }
            };
        }
    }

    private static void addItem(Item item, Element rootElement, Document doc) {
        Element itemElement = doc.createElement("item");

        Element descriptionElement = doc.createElement("description");
        descriptionElement.appendChild(doc.createTextNode(item.getDescription()));
        itemElement.appendChild(descriptionElement);

        Element valueElement = doc.createElement("value");
        valueElement.appendChild(doc.createTextNode(String.valueOf(item.getValue())));
        itemElement.appendChild(valueElement);

        rootElement.appendChild(itemElement);
    }

    private static void addSection(Section section, Element rootElement, Document doc) {
        Element sectionElement = doc.createElement("section");
        Attr attrDesc = doc.createAttribute("description");
        attrDesc.setValue(section.getDescription());
        sectionElement.setAttributeNode(attrDesc);
        Attr attrValue = doc.createAttribute("value");
        attrValue.setValue(String.valueOf(section.getValue()));
        sectionElement.setAttributeNode(attrValue);

        for(Item item: section.getItems()) {
            addItem(item, sectionElement, doc);
        }


        rootElement.appendChild(sectionElement);
    }

}
