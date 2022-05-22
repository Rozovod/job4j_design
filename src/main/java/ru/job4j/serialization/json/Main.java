package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final MobilePhone mobilePhone = new MobilePhone(
                "IPhone", "XS", 256, false,
                new Warranty(3, true), new String[] {"Case", "Protective glass"}
        );

        final Gson gson = new GsonBuilder().create();
        final String mobilePhoneJson = gson.toJson(mobilePhone);
        System.out.println(mobilePhoneJson);

        final MobilePhone mobilePhoneFromJson = gson.fromJson(mobilePhoneJson, MobilePhone.class);
        System.out.println(mobilePhoneFromJson);
    }
}
