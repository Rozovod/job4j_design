package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final MobilePhone mobilePhone = new MobilePhone(
                "IPhone", "XS", 256, false,
                new Warranty(3, true), new String[] {"Case", "Protective glass"}
        );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", mobilePhone.getName());
        jsonObject.put("model", mobilePhone.getModel());
        jsonObject.put("memory", mobilePhone.getMemory());
        jsonObject.put("headphoneJack", mobilePhone.isHeadphoneJack());
        jsonObject.put("warranty", mobilePhone.getWarranty());
        jsonObject.put("accessories", mobilePhone.getAccessories());

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(mobilePhone).toString());
    }
}
