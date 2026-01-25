
package test.plsql_workbench_examples.factoryapi;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import factory.ExamplesRPCFactory;
import service.XmlTypeDemoService;
import transferobject.XmlTypeDemoTO;

public class XmlTypeDemoTest {
  @Test
  public void getXmlType() throws Exception
  {
    // get service
    XmlTypeDemoService service = ExamplesRPCFactory.getXmlTypeDemoService();

    // fetch XML document from stored procedure
    Document doc = service.getXmlType();

    // format output
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    StreamResult result = new StreamResult(new StringWriter());
    DOMSource source = new DOMSource(doc);
    transformer.transform(source, result);
    String xmlString = result.getWriter().toString();

    System.out.println("fetch xml-document from stored procedure");
    System.out.println(xmlString);
  }

  @Test
  public void extractXmlType() throws Exception
  {
    // get service
    XmlTypeDemoService service = ExamplesRPCFactory.getXmlTypeDemoService();

    // build XML document
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    ByteArrayInputStream bais = new ByteArrayInputStream("<?xml version = '1.0' encoding = 'UTF-8'?><ROW><NAME>Tom</NAME><SURENAME>Jones</SURENAME></ROW>".getBytes("UTF-8"));
    Document doc = dBuilder.parse(bais);

    // call stored procedure with XML document
    XmlTypeDemoTO.ExtractFromXmlTypeTO result = service.extractFromXmlType(doc);

    System.out.println("\n\nextract values from xml-document inside stored procedure");
    System.out.println("name:" + result.name + " / surename:" + result.surename);
  }
}
