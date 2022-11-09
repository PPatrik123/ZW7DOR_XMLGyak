package xPathZW7DOR2;

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

public class xpathZW7DOR {
	 public static void main(String[] args) {
	        try {
	            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

	            Document document = documentBuilder.parse("C:/Programok codeb/ZW7DOR_1109/studentZW7DOR.xml");

	            document.getDocumentElement().normalize();

	            XPath xPath = XPathFactory.newInstance().newXPath();
	            
	            String expression = "/class/student";

	            NodeList nodelist = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

	            for(int i = 0; i < nodelist.getLength(); i++){
	                Node node = nodelist.item(i);

	                System.out.println("\n aktu�lis elem : "+node.getNodeName());

	                if(node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")){
	                    Element element = (Element) node;

	                    System.out.println("Hallgat� ID: "
	                    + element.getAttribute("id)"));

	                    System.out.println("Keresztn�v: "+ element.getElementsByTagName("keresztnev").item(0).getTextContent());

	                    System.out.println("Vezetekn�v: "+ element.getElementsByTagName("vezeteknev").item(0).getTextContent());

	                    System.out.println("Becen�v: "+ element.getElementsByTagName("becenev").item(0).getTextContent());

	                    System.out.println("Kor: "+ element.getElementsByTagName("kor").item(0).getTextContent());

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

