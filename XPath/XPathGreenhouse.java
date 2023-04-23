package HW3.XPath;

import HW3.generated.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XPathGreenhouse {
    private PlantType currentPlant;
    GreenhouseType greenhouse;

    public GreenhouseType getGreenhouse() throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        File file = new File("C:\\Users\\Artur\\IdeaProjects\\Homework3WebServices\\src\\HW3\\greenhouse.xml");
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();

        XPath path = XPathFactory.newInstance().newXPath();
        String expression = "/greenhouse/plant";
        NodeList nodeList = (NodeList) path.compile(expression).evaluate(document, XPathConstants.NODESET);

        greenhouse = new GreenhouseType();

        for(int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                currentPlant = new PlantType();
                currentPlant.setName(element.getElementsByTagName("name").item(0).getTextContent());
                currentPlant.setSoil(element.getElementsByTagName("soil").item(0).getTextContent());
                currentPlant.setOrigin(element.getElementsByTagName("origin").item(0).getTextContent());

                currentPlant.setVisualParameters(new VisualParametersType());
                currentPlant.getVisualParameters().setMediumSize(new MediumSizeType());

                currentPlant.getVisualParameters().setStemColor(element.getElementsByTagName("stem_color").item(0).getTextContent());
                currentPlant.getVisualParameters().setLeafsColor(element.getElementsByTagName("leafs_color").item(0).getTextContent());
                currentPlant.getVisualParameters().getMediumSize().setUnit(element.getElementsByTagName("medium_size").item(0).getAttributes().item(0).getTextContent());
                currentPlant.getVisualParameters().getMediumSize().setValue(Integer.parseInt(element.getElementsByTagName("medium_size").item(0).getTextContent()));

                currentPlant.setGrowingTips(new GrowingTipsType());
                currentPlant.getGrowingTips().setTemperature(new TemperatureType());
                currentPlant.getGrowingTips().setWatering(new WateringType());

                currentPlant.getGrowingTips().getTemperature().setUnit(element.getElementsByTagName("temperature").item(0).getAttributes().item(0).getTextContent());
                currentPlant.getGrowingTips().getTemperature().setValue(Integer.parseInt(element.getElementsByTagName("temperature").item(0).getTextContent()));
                currentPlant.getGrowingTips().setLighting(element.getElementsByTagName("lighting").item(0).getTextContent());
                currentPlant.getGrowingTips().getWatering().setUnit(element.getElementsByTagName("watering").item(0).getAttributes().item(0).getTextContent());
                currentPlant.getGrowingTips().getWatering().setValue(element.getElementsByTagName("watering").item(0).getTextContent());

                greenhouse.getPlant().add(currentPlant);
                currentPlant = null;
            }
        }
        return greenhouse;

        /*IXPathExpression exprPlant = path.compile("//plant");
        XPathExpression exprSoil = path.compile("//plant/soil");
        XPathExpression exprOrigin = path.compile("//plant/origin");
        XPathExpression exprStemColor = path.compile("//plant/stem_color");
        XPathExpression exprLeafsColor = path.compile("//plant/leafs_color");
        XPathExpression exprMediumSize = path.compile("//plant/medium_size");
        XPathExpression exprTemperature= path.compile("//plant/temperature");
        XPathExpression exprLighting = path.compile("//plant/lighting");
        XPathExpression exprWatering = path.compile("//plant/watering");

        File file = new File("C:\\Users\\Artur\\IdeaProjects\\Homework3WebServices\\src\\HW3\\greenhouse.xml");



        nputSource source = new InputSource(new FileInputStream(file));
        Object resultPlant = exprPlant.evaluate(source, XPathConstants.NODESET);
        NodeList plant = (NodeList) resultPlant;

        source = new InputSource(new FileInputStream(file));
        Object resultSoil = exprSoil.evaluate(source, XPathConstants.NODESET);
        NodeList soil = (NodeList) resultSoil;

        source = new InputSource(new FileInputStream(file));
        Object resultOrigin = exprOrigin.evaluate(source, XPathConstants.NODESET);
        NodeList origin = (NodeList) resultOrigin;

        source = new InputSource(new FileInputStream(file));
        Object resultStemColor = exprPlant.evaluate(source, XPathConstants.NODESET);
        NodeList stemColor = (NodeList) resultPlant;

        source = new InputSource(new FileInputStream(file));
        Object resultSoil = exprSoil.evaluate(source, XPathConstants.NODESET);
        NodeList soil = (NodeList) resultSoil;

        source = new InputSource(new FileInputStream(file));
        Object resultOrigin = exprOrigin.evaluate(source, XPathConstants.NODESET);
        NodeList origin = (NodeList) resultOrigin;

        source = new InputSource(new FileInputStream(file));
        Object resultPlant = exprPlant.evaluate(source, XPathConstants.NODESET);
        NodeList plant = (NodeList) resultPlant;

        source = new InputSource(new FileInputStream(file));
        Object resultSoil = exprSoil.evaluate(source, XPathConstants.NODESET);
        NodeList soil = (NodeList) resultSoil;

        source = new InputSource(new FileInputStream(file));
        Object resultOrigin = exprOrigin.evaluate(source, XPathConstants.NODESET);
        NodeList origin = (NodeList) resultOrigin;*/
    }

}
