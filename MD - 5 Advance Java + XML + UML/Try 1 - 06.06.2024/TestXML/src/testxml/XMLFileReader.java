package testxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLFileReader {

    public static void main(String[] args) {
        try {
            // Create a DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            // Use the factory to create a DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML file to create a Document object
            Document document = builder.parse("employee.xml");

            // Normalize the XML structure
            document.getDocumentElement().normalize();

            // Get the root element
            Element root = document.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());

            // Get all employee nodes
            NodeList nodeList = document.getElementsByTagName("employee");

            // Loop through each employee node
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Get and print each element's value
                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String position = element.getElementsByTagName("position").item(0).getTextContent();
                    String salary = element.getElementsByTagName("salary").item(0).getTextContent();

                    System.out.println("Employee ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Position: " + position);
                    System.out.println("Salary: " + salary);
                    System.out.println("---------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
