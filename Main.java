package HW3;

import HW3.XPath.XPathGreenhouse;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import HW3.StAX.StAXHandler;
import HW3.generated.GreenhouseType;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        XPathGreenhouse xPathGreenhouse = new XPathGreenhouse();
        GreenhouseType greenhouse = xPathGreenhouse.getGreenhouse();

        greenhouse.show();

    }
}