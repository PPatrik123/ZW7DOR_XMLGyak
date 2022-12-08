package hu.domparse.ZW7DOR;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		// TODO Auto-generated method stub
		
		//XML f�jl megh�v�sa
		File xmlFile = new File("XMLZW7DOR.xml");
		
		//Document builder defini�l�sa
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//F�jl bet�lt�se a documentum builderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//Fut�rok kilist�z�sa
		
		System.out.println("");
		System.out.println("Fut�rok kilistazasa: ");
		System.out.println("");
		
		NodeList futarList = doc.getElementsByTagName("futar");
		for(int i=0;i<futarList.getLength();i++) {
			Node nNode = futarList.item(i);
			printFutar(nNode);
		}
		
		//Csokis zserb� �tel kilist�z�sa
		System.out.println("");
		System.out.println("Csokis zserb� �tel kilist�z�sa: ");
		System.out.println("");
		
		NodeList etelList = doc.getElementsByTagName("etel");
		for(int i=0;i<etelList.getLength();i++) {
			Node nNode = etelList.item(i);
			printFutar(nNode, "Csokis zserb�");
		}
		
		// 3400 feletti �tel ki list�z�sa
		
		System.out.println("");
		System.out.println("3400ft-os �tel kilistazasa");
		System.out.println("");
		
		NodeList otezerList = doc.getElementsByTagName("etel");
		for(int i=0;i<otezerList.getLength();i++) {
			Node nNode = otezerList.item(i);
			printOtezer(nNode, "3400");
		}
		
		
		System.out.println("");
		System.out.println("Bankk�rtya kilist�z�sa ");
		//bankkk�rtya kilist�z�sa m�sik megold�ssal
		
		NodeList bankkartyaList =doc.getElementsByTagName("bankartya");
		
		for(int i=0;i<bankkartyaList.getLength();i++) {
			Node nNode = bankkartyaList.item(i);
			System.out.println("");
			printbankkartya(nNode);
		}
		
		//vev�k kilist�z�sa
		
		System.out.println("");
		System.out.println("vev�k kilist�z�sa");
		
		NodeList vevoList = doc.getElementsByTagName("vevo");
		for(int i=0;i<vevoList.getLength();i++) {
			Node nNode = vevoList.item(i);
			System.out.println("");
			printvevo(nNode);
		}
		
	}
	
	//Fut�rok kilist�z�sa
	private static void printFutar(Node nNode) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String F_ID = elem.getAttribute("F_ID");
			
			Node nNode1 = elem.getElementsByTagName("Nev").item(0);
			String Nev = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("Telefonszam").item(0);
			String Telefonszam = nNode2.getTextContent();
			
			
			
			
			System.out.printf("F_ID: %s%n", F_ID);
			System.out.printf("Nev: %s%n", Nev);
			System.out.printf("Telefonszam %s%n", Telefonszam);
			
			System.out.println("");
		}
	}

	//�telek ki list�z�sa
	private static void printFutar(Node nNode, String Nev) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String F_ID = elem.getAttribute("Etel_ID");
			
			Node nNode1 = elem.getElementsByTagName("etel_neve").item(0);
			String Nev2 = nNode1.getTextContent();
			
			Node nNode2 = elem.getElementsByTagName("teljes_ar").item(0);
			String teljes_ar = nNode2.getTextContent();
			
			
			
			if(Nev2.equals(Nev)) {
				System.out.printf("F_ID: %s%n", F_ID);
				System.out.printf("Etel neve: %s%n", Nev2);
				System.out.printf("Teljes ar: %s%n", teljes_ar);
				
				System.out.println("");
			}
		}
	}
	
	// 3400ft-os �telek kilist�z�sa
	private static void printOtezer(Node nNode, String teljes_ar) {
		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element elem = (Element) nNode;
			String F_ID = elem.getAttribute("Etel_ID");
			
			Node nNode1 = elem.getElementsByTagName("etel_neve").item(0);
			String Felszereles = nNode1.getTextContent();
			
			
			
			Node nNode5 = elem.getElementsByTagName("teljes_ar").item(0);
			String teljes_ar2 = nNode5.getTextContent();
			
			if(teljes_ar2.equals(teljes_ar)) {
				System.out.printf("Etel_ID: %s%n", F_ID);
				System.out.printf("Etel neve: %s%n", Felszereles);
				
				System.out.printf("teljes_ar %s%n", teljes_ar);
				System.out.println("");
			}
		}
	}
	
	//bankkartyak kilist�z�sa m�sik megold�ssal
	private static void printbankkartya(Node nNode) {
		if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			Element element =(Element) nNode;
			NodeList nList = element.getChildNodes();
			for (int j = 0; j < nList.getLength(); j++) {
                Node node2 = nList.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                }
			}
		}	
	}
	
	//vevook kilist�z�sa
	private static void printvevo(Node nNode) {
		if(nNode.getNodeType()==Node.ELEMENT_NODE) {
			Element element =(Element) nNode;
			NodeList nList = element.getChildNodes();
			for (int j = 0; j < nList.getLength(); j++) {
                Node node2 = nList.item(j);
                if (node2.getNodeType() == Node.ELEMENT_NODE) {
                    Element element2 = (Element) node2;
                    System.out.println(node2.getNodeName()+" : "+node2.getTextContent());
                }
			}
		}	
	}
}