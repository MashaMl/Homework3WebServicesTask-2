package HW3.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import HW3.generated.*;

public class SAXHandler extends DefaultHandler {
    private String currentQName;
    private GreenhouseType greenhouse;
    private PlantType currentPlant;
    private String currentAttribute;

    @Override
    public void startDocument() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentQName = qName;
        if(attributes.getLength() > 0){
            currentAttribute = attributes.getValue(0);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length);
        switch (currentQName) {
            case"greenhouse":
                greenhouse = new GreenhouseType();
            case"plant":
                currentPlant = new PlantType();
                break;
            case"name":
                currentPlant.setName(value);
                break;
            case"soil":
                currentPlant.setSoil(value);
                break;
            case"origin":
                currentPlant.setOrigin(value);
                break;
            case"visual_parameters":
                currentPlant.setVisualParameters(new VisualParametersType());
                break;
            case"stem_color":
                currentPlant.getVisualParameters().setStemColor(value);
                break;
            case"leafs_color":
                currentPlant.getVisualParameters().setLeafsColor(value);
                break;
            case"medium_size":
                currentPlant.getVisualParameters().setMediumSize(new MediumSizeType());
                currentPlant.getVisualParameters().getMediumSize().setUnit(currentAttribute);
                int mediumSizeValue = new Integer(value);
                currentPlant.getVisualParameters().getMediumSize().setValue(mediumSizeValue);
                break;
            case"growing_tips":
                currentPlant.setGrowingTips(new GrowingTipsType());
                break;
            case"temperature":
                currentPlant.getGrowingTips().setTemperature(new TemperatureType());
                currentPlant.getGrowingTips().getTemperature().setUnit(currentAttribute);
                int temperatureValue = new Integer(value);
                currentPlant.getGrowingTips().getTemperature().setValue(temperatureValue);
                break;
            case"lighting":
                currentPlant.getGrowingTips().setLighting(value);
                break;
            case"watering":
                currentPlant.getGrowingTips().setWatering(new WateringType());
                currentPlant.getGrowingTips().getWatering().setUnit(currentAttribute);
                currentPlant.getGrowingTips().getWatering().setValue(value);
                break;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        currentQName = "";
        if(qName.equals("plant")){
            greenhouse.getPlant().add(currentPlant);
        }
    }

    @Override
    public void endDocument() {
        currentPlant = null;
    }

    public GreenhouseType getGreenhouse(){
        return greenhouse;
    }

}
