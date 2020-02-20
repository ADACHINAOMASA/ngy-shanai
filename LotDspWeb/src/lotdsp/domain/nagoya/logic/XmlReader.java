package lotdsp.domain.nagoya.logic;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *  XMLファイルを読み込むクラス
 *  @param XMLファイルのパス
 *  return List
 */
public class XmlReader {

  public List domRead(String file) throws SAXException, IOException, ParserConfigurationException {
	// XMLドキュメントを取得
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	DocumentBuilder documentBuilder = factory.newDocumentBuilder();
	Document doc = documentBuilder.parse(file);

	Element node = doc.getDocumentElement();
	// IPタグを取得
	NodeList persons = node.getElementsByTagName("permit");

	List permitList = new ArrayList();
	XmlMsg xmlMsg = new XmlMsg();
	Element person = null;
	for(int i = 0; i < persons.getLength(); i++) {
	  xmlMsg = new XmlMsg();
	  person = (Element)persons.item(i);
	  //String name = person.getElementsByTagName("name").item(0).getFirstChild().getNodeValue();
	  //String ip = person.getElementsByTagName("ip").item(0).getFirstChild().getNodeValue();

	  xmlMsg.setIp(person.getElementsByTagName("ip").item(0).getFirstChild().getNodeValue());
	  xmlMsg.setName(person.getElementsByTagName("name").item(0).getFirstChild().getNodeValue());
	  xmlMsg.setMode(person.getElementsByTagName("mode").item(0).getFirstChild().getNodeValue());

	  permitList.add(xmlMsg);
	}

	return permitList;
  }

}
