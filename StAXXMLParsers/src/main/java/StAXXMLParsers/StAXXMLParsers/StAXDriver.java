package StAXXMLParsers.StAXXMLParsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class StAXDriver 
{
    /***
     * Main method that would drive the application
     * @param args
     * @throws FileNotFoundException
     * @throws XMLStreamException
     * @throws FactoryConfigurationError
     * @throws TransformerException 
     */
	public static void main( String[] args ) throws FileNotFoundException, XMLStreamException, FactoryConfigurationError, TransformerException
    {
      //  System.out.println((new StAXStreamHandler()).processXMLFile(new File("resources/student.xml")).toString());
		
		String rawXML = (new StAXStreamHandler()).processXMLFile(new File("resources/student.xml")).toString();
		
		System.out.println(StAXDriver.transformXML(3, rawXML));
		
		
    }
    
	/***
	 * Method that transforms the XML 
	 * @param indentation
	 * @param rawXML
	 * @return
	 * @throws TransformerException 
	 */
    public static String transformXML(int indentation, String rawXML) throws TransformerException {
    	
    	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	transformerFactory.setAttribute("indent-number", indentation);
    	
    	Transformer transformer = transformerFactory.newTransformer();
    	transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    	
    	StreamResult streamResult = new StreamResult(new StringWriter());
    	
    	transformer.transform(new StreamSource(new StringReader(rawXML)), streamResult);
    	    	
    	return streamResult.getWriter().toString();
    }
}
