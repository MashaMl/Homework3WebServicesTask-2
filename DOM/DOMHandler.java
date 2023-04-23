package HW3.DOM;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import HW3.generated.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMHandler {
    Document document;
    public DOMHandler() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        document = documentBuilder.parse(new File("C:\\Users\\Artur\\IdeaProjects\\homework1Web_services\\src\\pachage\\greenhouse.xml"));
    }

    public GreenhouseType getGreenhouse() {
        GreenhouseType greenhouse = new GreenhouseType();

        NodeList list = document.getElementsByTagName("plant");
        for(int i = 0; i < list.getLength(); ++i) {


            Element plantElement = (Element) list.item(i);
            String name = plantElement.getElementsByTagName("name").item(0).getTextContent();
            String soil = plantElement.getElementsByTagName("soil").item(0).getTextContent();
            String origin = plantElement.getElementsByTagName("origin").item(0).getTextContent();

            Element visualParametersElement = (Element) plantElement.getElementsByTagName("visual_parameters").item(0);

            String stemColor = visualParametersElement.getElementsByTagName("stem_color").item(0).getTextContent();
            String leafsColor = visualParametersElement.getElementsByTagName("leafs_color").item(0).getFirstChild().getTextContent();
            String mediumSizeUnit = visualParametersElement.getElementsByTagName("medium_size").item(0).getAttributes().item(0).getTextContent();
            int mediumSizeValue = Integer.parseInt(visualParametersElement.getElementsByTagName("medium_size").item(0).getFirstChild().getTextContent());

            Element growintRipsElement = (Element) plantElement.getElementsByTagName("growing_tips").item(0);
            String temperatureUnit = growintRipsElement.getElementsByTagName("temperature").item(0).getAttributes().item(0).getTextContent();
            int temperatureValue = Integer.parseInt(growintRipsElement.getElementsByTagName("temperature").item(0).getFirstChild().getTextContent());
            String wateringUnit = growintRipsElement.getElementsByTagName("watering").item(0).getAttributes().item(0).getTextContent();
            String wateringValue = growintRipsElement.getElementsByTagName("watering").item(0).getFirstChild().getTextContent();
            String lighting = growintRipsElement.getElementsByTagName("lighting").item(0).getFirstChild().getTextContent();

            PlantType plant = new PlantType();
            plant.setName(name);
            plant.setSoil(soil);
            plant.setOrigin(origin);

            VisualParametersType visualParameters = new VisualParametersType();

            MediumSizeType mediumSize = new MediumSizeType();
            mediumSize.setUnit(mediumSizeUnit);
            mediumSize.setValue(mediumSizeValue);

            visualParameters.setMediumSize(mediumSize);
            visualParameters.setLeafsColor(leafsColor);
            visualParameters.setStemColor(stemColor);

            plant.setVisualParameters(visualParameters);

            GrowingTipsType growingTips = new GrowingTipsType();

            TemperatureType temperature = new TemperatureType();
            temperature.setUnit(temperatureUnit);
            temperature.setValue(temperatureValue);

            WateringType watering = new WateringType();
            watering.setUnit(wateringUnit);
            watering.setValue(wateringValue);

            growingTips.setWatering(watering);
            growingTips.setLighting(lighting);
            growingTips.setTemperature(temperature);

            plant.setGrowingTips(growingTips);
            greenhouse.getPlant().add(plant);
        }
        return greenhouse;
    }
}
