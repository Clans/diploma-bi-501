package chstu.clans.mycms;

/**
 *
 * @author Clans
 */
import java.io.*;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class RemoveBlock_unused {

  static public void main(String[] arg) {
    try{
      String xmlFile = "Management.xml";
      File file = new File(xmlFile);
      String remElement = "Physical_Order_List_Array";
      if (file.exists()){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer tFormer = tFactory.newTransformer();
        Element element = (Element)doc.getElementsByTagName(remElement).item(0);
//        Remove the node
        element.getParentNode().removeChild(element);
//              Normalize the DOM tree to combine all adjacent nodes
        doc.normalize();
        Source source = new DOMSource(doc);
        Result dest = new StreamResult(System.out);
        tFormer.transform(source, dest);
       // System.out.println();
       // saveStringToFile("emir.xml", "dsds"); -> write method
      }
      else{
        System.out.println("File not found!");
      }
    }
    catch (Exception e){
      System.err.println(e);
      System.exit(0);
    }
  }
}
