package chstu.clans.mycms;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Clans
 */
public class XMLWriter {

    /*private String configFile;

    public void setFile(String configFile) {
        this.configFile = configFile;
    }*/

    public void saveConfig(File configFile, File dir) throws Exception {
        // Create a XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        // Create XMLEventWriter
        XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new 
                FileOutputStream(configFile));

        // Create a EventFactory
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        
        // Create and write Start Tag
        StartDocument startDocument = eventFactory.createStartDocument();
        eventWriter.add(startDocument);

        SimpleDateFormat dateformatter = new SimpleDateFormat("yyyyMMdd HH:mm:ssZ");
        //File dir = new File("d:\\Distrib\\!!!Saves!!!\\CCleaner");
        String[] files = dir.list();

        // Create config open tag
        StartElement configStartElement = eventFactory.createStartElement("", "", "config");
        eventWriter.add(end);
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        for (int i = 0; i < files.length; i++) {
            File f = new File(dir + File.separator + files[i]);
            String size = Long.toString(f.length());
            Date d = new Date(f.lastModified());
            String modified = dateformatter.format(d.getTime());

            // Create file open tag
            StartElement filenameStartElement = eventFactory.createStartElement("", "", "file");
            eventWriter.add(tab);
            eventWriter.add(filenameStartElement);
            eventWriter.add(eventFactory.createAttribute("name", f.getName()));
            eventWriter.add(end);

            // Write the different nodes
            createNode(eventWriter, "size", size);
            createNode(eventWriter, "modified", modified);
            createNode(eventWriter, "lastsync", "null");

            // Create file close root
            EndElement filenameEndElement = eventFactory.createEndElement("", "", "");
            eventWriter.add(tab);
            eventWriter.add(filenameEndElement);
            eventWriter.add(end);
        }

        // Create config close tag
        eventWriter.add(eventFactory.createEndElement("", "", "config"));
        eventWriter.add(end);
        eventWriter.add(eventFactory.createEndDocument());
        eventWriter.close();
    }

    private void createNode(XMLEventWriter eventWriter, String name,
            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        // Create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(tab);
        eventWriter.add(sElement);

        // Create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        
        // Create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}
