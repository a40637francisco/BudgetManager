package javaa.main.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import javaa.main.model.dataAPI.model.DtoBudget;
import javaa.main.model.dataAPI.model.DtoItem;
import javaa.main.model.dataAPI.model.DtoSection;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Created on 14/08/2016.
 */
public class ReadXMLFile {

    public static void readBudgetXML(String fileName, Consumer<DtoBudget> callback) {
        try {
            File fXmlFile = new File("documents/" + fileName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            DtoBudget budget = new DtoBudget();
            budget.setName(doc.getDocumentElement().getAttribute("name"));
            budget.setAddress(doc.getElementsByTagName("address").item(0).getTextContent());

            NodeList nList = doc.getElementsByTagName("items");

            Node child = nList.item(0).getFirstChild();
            while (child != null) {
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) child;
                    System.out.println(childElement.getTagName()+":");
                    if(childElement.getTagName().equals("item")) {
                        DtoItem item = new DtoItem();
                        item.setDescription(childElement.getElementsByTagName("description").item(0).getTextContent());
                        item.setValue(Integer.parseInt(childElement.getElementsByTagName("value").item(0).getTextContent()));
                        budget.addToItems(item);
                        System.out.println(childElement.getElementsByTagName("description").item(0).getTextContent());
                        System.out.println(childElement.getElementsByTagName("value").item(0).getTextContent());
                    } else if(childElement.getTagName().equals("section")) {
                        DtoSection section = new DtoSection();
                        section.setDescription(childElement.getAttribute("description"));
                        section.setValue(Integer.parseInt(childElement.getAttribute("value")));

                        System.out.println(childElement.getAttribute("description"));
                        System.out.println(childElement.getAttribute("value"));
                        Node innerItem = childElement.getFirstChild();
                        while(innerItem != null) {
                            if (innerItem.getNodeType() == Node.ELEMENT_NODE) {
                                Element innerChildElement = (Element) innerItem;
                                System.out.println(innerChildElement.getTagName() + ":");
                                if (innerChildElement.getTagName().equals("item")) {
                                    DtoItem item = new DtoItem();
                                    item.setDescription(innerChildElement.getElementsByTagName("description").item(0).getTextContent());
                                    item.setValue(Integer.parseInt(innerChildElement.getElementsByTagName("value").item(0).getTextContent()));
                                    section.addItem(item);

                                    System.out.println(innerChildElement.getElementsByTagName("description").item(0).getTextContent());
                                    System.out.println(innerChildElement.getElementsByTagName("value").item(0).getTextContent());
                                }
                            }
                            innerItem = innerItem.getNextSibling();
                        }
                        budget.addToItems(section);
                    }

                }
                child = child.getNextSibling();
            }

            callback.accept(budget);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void readItemsXML(Consumer<ArrayList<DtoItem>> cb) {
        try {
            File fXmlFile = new File("items/items.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            ArrayList<DtoItem> list = new ArrayList<>();
            NodeList nList = doc.getElementsByTagName("items");
            Node child = nList.item(0).getFirstChild();
            while (child != null) {
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) child;
                    DtoItem item = new DtoItem();
                    item.setDescription(childElement.getElementsByTagName("description").item(0).getTextContent());
                    item.setValue(Integer.parseInt(childElement.getElementsByTagName("value").item(0).getTextContent()));
                    list.add(item);
                }
                child = child.getNextSibling();
            }
            cb.accept(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





}
