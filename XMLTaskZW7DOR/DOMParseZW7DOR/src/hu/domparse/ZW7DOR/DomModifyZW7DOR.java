package hu.domparse.ZW7DOR;


import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
public class DomModifyZW7DOR {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		// TODO Auto-generated method stub

		//XML fájl meghívása
		File xmlFile = new File("XMLZW7DOR.xml");
		
		//Document builder definiálása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		//Fájl betöltése a DocumentBuilderbe
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		
		//A 1-es id-val rendelkezõ futar nevét változtatjuk
		NodeList nodes = doc.getElementsByTagName("futar");
		for(int i=0;i<nodes.getLength();i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				if(node.getAttributes().getNamedItem("F_ID").getTextContent().equals("1")) {
					NodeList childNodes = node.getChildNodes();
					for(int j=0;j<childNodes.getLength();j++) {
						Node childNode = childNodes.item(j);
						if(childNode.getNodeName().equals("Nev")) {
							childNode.setTextContent("Lajos Balázs");
						}
						
					}
				}
			}
		}
		
		//Új futárt adunk hozzá
		Element futar = (Element)doc.getElementsByTagName("futarok").item(0);
		futar.appendChild(createFutar(doc,"4","Katona János", "200000"));
		//ÚJ ételt adunk hozzá
		Element etel = (Element)doc.getElementsByTagName("etelek").item(0);
		etel.appendChild(createEtel(doc,"4", "Mákos Guba", "7000","4"));
		//Módosítjuk a 1-es id-vel rendelkező beszállítót 4-es id-re
		modifyId(doc, "beszallito", "Be_ID", "1", "4");
		//Módosítjuk a 1-es id-vel rendelkező éttermet 4-es id-re
		modifyId(doc, "etterem", "ET_ID", "1", "4");
		
		//Lementjük a módosított dokumentumot
		SaveAsDoc(doc, "teszt.xml");
	}
	
	//Lementjük a módosított xml dokumentumot
	public static void SaveAsDoc(Document doc, String filename) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{https://xml.apache.org/.xslt}indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		
		File myFile = new File(filename);
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
	
	//futárt hozzunk létre
	private static Node createFutar(Document doc, String F_ID, String nev, String Telefonszam) {
		
		Element user = doc.createElement("futar");
		
		user.setAttribute("F_ID", F_ID);
		user.appendChild(createElement(doc, "Nev", nev));
		user.appendChild(createElement(doc, "Telefonszam", Telefonszam));
		
		
		return user;
	}
	
	
	//ételt hozzunk létre
	private static Node createEtel(Document doc, String Etel_ID, String etel_neve, String teljes_ar, String ET_IDREF) {
		
		Element user = doc.createElement("etel");
		
		user.setAttribute("Etel_ID", Etel_ID);
		user.appendChild(createElement(doc, "etel_neve", etel_neve));
		user.appendChild(createElement(doc, "teljes_ar", teljes_ar));
		user.appendChild(createElement(doc, "ET_IDREF", ET_IDREF));
		

		
		return user;
	}
	
	//Elementet hozzunk létre
	private static Node createElement(Document doc, String name, String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	//Módosítjuk az id-t
	
	public static void modifyId(Document doc, String tagName, String attrName, String oldData, String newData) {

		NodeList nodes = doc.getElementsByTagName(tagName);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getAttributes().getNamedItem(attrName).getTextContent().equals(oldData)) {
					node.getAttributes().getNamedItem(attrName).setTextContent(newData);
				}
			}
		}
	}

}