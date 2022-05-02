package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean modOn = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] ln = line.split(" ");
                if (modOn && Objects.equals(ln[0], "400")
                        || modOn && Objects.equals(ln[0], "500")) {
                    out.print(ln[1] + ";");
                    modOn = false;
                } else if (!modOn && Objects.equals(ln[0], "200")
                        || !modOn && Objects.equals(ln[0], "300")) {
                    out.println(ln[1] + ";");
                    modOn = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./server.log", "./target.log");
        Analizy analizy2 = new Analizy();
        analizy2.unavailable("./server2.log", "./target2.log");
    }
}
