package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "mobilePhone")
@XmlAccessorType(XmlAccessType.FIELD)
public class MobilePhone {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String model;
    @XmlAttribute
    private int memory;
    @XmlAttribute
    private boolean headphoneJack;
    private Warranty warranty;
    @XmlElementWrapper(name = "accessories")
    @XmlElement(name = "accessory")
    private String[] accessories;

    public MobilePhone() {

    }

    public MobilePhone(String name, String model, int memory, boolean headphoneJack, Warranty warranty, String[] accessories) {
        this.name = name;
        this.model = model;
        this.memory = memory;
        this.headphoneJack = headphoneJack;
        this.warranty = warranty;
        this.accessories = accessories;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getMemory() {
        return memory;
    }

    public boolean isHeadphoneJack() {
        return headphoneJack;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public String[] getAccessories() {
        return accessories;
    }

    @Override
    public String toString() {
        return "MobilePhone{"
                + "name='" + name + '\''
                + ", model='" + model + '\''
                + ", memory=" + memory
                + ", headphoneJack=" + headphoneJack
                + ", warranty=" + warranty
                + ", accessories=" + Arrays.toString(accessories)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        MobilePhone mobilePhone = new MobilePhone(
                "IPhone", "XS", 256, false,
                new Warranty(3, true), new String[] {"Case", "Protective glass"}
        );
        JAXBContext context = JAXBContext.newInstance(MobilePhone.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(mobilePhone, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            MobilePhone result = (MobilePhone) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
