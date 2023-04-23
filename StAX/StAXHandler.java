package HW3.StAX;

import HW3.generated.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class StAXHandler {

    private PlantType currentPlant;
    public GreenhouseType getGreenhouse() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        Reader reader = new FileReader("C:\\Users\\Artur\\IdeaProjects\\homework1Web_services\\src\\pachage\\greenhouse.xml");
        XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(reader);
        GreenhouseType greenhouse = new GreenhouseType();
        while (xmlStreamReader.hasNext()){
            switch (xmlStreamReader.next()) {
                case XMLStreamReader.START_ELEMENT:
                    String element = xmlStreamReader.getName().toString();
                    switch (element){
                        case"plant":
                            currentPlant = new PlantType();
                            currentPlant.setGrowingTips(new GrowingTipsType());
                            currentPlant.setVisualParameters(new VisualParametersType());
                            currentPlant.getVisualParameters().setMediumSize(new MediumSizeType());
                            currentPlant.getGrowingTips().setTemperature(new TemperatureType());
                            currentPlant.getGrowingTips().setWatering(new WateringType());
                            break;
                        case"name":
                            currentPlant.setName(xmlStreamReader.getElementText());
                            break;
                        case"soil":
                            currentPlant.setSoil(xmlStreamReader.getElementText());
                            System.out.println(currentPlant.getSoil());
                            break;
                        case"origin":
                            currentPlant.setOrigin(xmlStreamReader.getElementText());
                            break;
                        case"stem_color":
                            currentPlant.getVisualParameters().setStemColor(xmlStreamReader.getElementText());
                            break;
                        case"leafs_color":
                            currentPlant.getVisualParameters().setLeafsColor(xmlStreamReader.getElementText());
                            break;
                        case"medium_size":
                            currentPlant.getVisualParameters().getMediumSize().setValue(Integer.parseInt(xmlStreamReader.getElementText()));
                            break;
                        case"temperature":
                            currentPlant.getGrowingTips().getTemperature().setValue(Integer.parseInt(xmlStreamReader.getElementText()));
                            break;
                        case"watering":
                            currentPlant.getGrowingTips().getWatering().setValue(xmlStreamReader.getElementText());
                            greenhouse.getPlant().add(currentPlant);
                            break;
                        case"lighting":
                            currentPlant.getGrowingTips().setLighting(xmlStreamReader.getElementText());
                            break;
                    }
                case XMLStreamConstants.END_ELEMENT:
                    break;
                    case XMLStreamConstants.ATTRIBUTE:
                        String elementA = xmlStreamReader.getName().toString();
                        switch (elementA){
                            case"medium_size":
                                currentPlant.getVisualParameters().getMediumSize().setUnit(xmlStreamReader.getAttributeValue(0));
                                break;
                            case"temperature":
                                currentPlant.getGrowingTips().getTemperature().setUnit(xmlStreamReader.getAttributeValue(0));
                                break;
                            case"watering":
                                currentPlant.getGrowingTips().getWatering().setUnit(xmlStreamReader.getAttributeValue(0));
                                break;
                        }
            }
        }
        return greenhouse;
    }
}
