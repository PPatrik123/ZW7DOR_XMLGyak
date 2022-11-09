package xpathkrplnb;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

public class XPathZW7DOR {
    public static void main{String[] args} {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse("student.xml");

            document.getDocumentElement().normalize();

            Xpath xPath = XPathFactory.newInstance().newXPath();

            NodeList nodelist = {NodeList} xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

            for(int i = 0; i < nodelist.getLenght(); i++){
                Node node = nodeList.item(i);

                System.out.println("\n aktuális elem : "+node.getNodeName());

                if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")){
                    Element element = {Element} node;

                    System.out.println("Hallgató ID: "
                    + element.getAttribute("id)"));

                    System.out.println("Keresztnév: "+ element.getElementsByTagName("keresztnev").item(0).getTextcontent());

                    System.out.println("Vezeteknév: "+ element.getElementsByTagName("vezeteknev").item(0).getTextcontent());

                    System.out.println("Becenév: "+ element.getElementsByTagName("becenev").item(0).getTextcontent());

                    System.out.println("Kor: "+ element.getElementsByTagName("kor").item(0).getTextcontent());

                }
            }
            
        } catch (ParserConfigurationException e){
            e.printStackTrace();
            
        } catch (SAXException e) {
            e.printStackTrace();

        } catch(IOException e) {
            e.printStackTrace();

        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}