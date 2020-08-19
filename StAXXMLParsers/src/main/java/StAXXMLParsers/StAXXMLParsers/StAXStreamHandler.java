package StAXXMLParsers.StAXXMLParsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


public class StAXStreamHandler {

	public StringBuffer processXMLFile(File xmlFile) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError {
		StringBuffer rawXML = new StringBuffer();
		
		XMLStreamReader xmlStreamReader = (XMLInputFactory.newInstance()).createXMLStreamReader(new FileInputStream(xmlFile));
		
		while(xmlStreamReader.hasNext()) {
			switch(xmlStreamReader.next()) {
			case XMLStreamConstants.START_ELEMENT:
				rawXML.append("<"+xmlStreamReader.getLocalName()+">");
				break;
			case XMLStreamConstants.CHARACTERS:
				if(!xmlStreamReader.isWhiteSpace()) rawXML.append(xmlStreamReader.getText());
				break;
			case XMLStreamConstants.END_ELEMENT:
				rawXML.append("</"+xmlStreamReader.getLocalName()+">");
				break;
			
			}
		}
		return rawXML;
	}

}
