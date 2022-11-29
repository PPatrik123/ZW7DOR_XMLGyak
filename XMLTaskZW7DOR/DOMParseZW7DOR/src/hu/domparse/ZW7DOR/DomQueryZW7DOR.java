package hu.domparse.ZW7DOR;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DomQueryZW7DOR {

	public static void main(String[] args) {

        
        File xmlFile = new File("XMLZW7DOR.xml");
        Document doc = introduceFile(xmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        } else {
            System.out.println("Document is null");
            System.exit(-1);
        }

        
        NodeList queryList = doc.getDocumentElement().getElementsByTagName("etel");

       
        for (int i = 0; i < queryList.getLength(); i++) {
            NodeList query = queryList.item(i).getChildNodes();
            for (int j = 0; j < query.getLength(); j++) {
                if (query.item(j).getNodeName().equals("teljes_ar") && Integer.parseInt(query.item(j).getTextContent()) > 3000){
                    System.out.println("{etel}");
                    listData(queryList.item(i).getChildNodes(), "");
                }
            }
        }
    }

    public static Document introduceFile (File xmlFile){
        Document doc = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            doc = dbBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void listData(NodeList nodeList, String indent){
        indent += "\t";

        if (nodeList != null) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE && !node.getTextContent().trim().isEmpty()) {
                    System.out.println(indent + "{" + node.getNodeName() + "}:");
                    NodeList nodeList_new = node.getChildNodes();
                    listData(nodeList_new, indent);
                } else if (node instanceof Text){
                    String value = node.getNodeValue().trim();
                    if (value.isEmpty()){
                        continue;
                    }
                    System.out.println(indent + node.getTextContent());
                }
            }
        }
    }
	
}
