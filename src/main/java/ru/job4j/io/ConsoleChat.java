package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswer;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    public void run() {
        List<String> botAnswerList = readPhrases(botAnswer);
        List<String> log = new ArrayList<>();
        boolean modOn = true;
        Scanner in = new Scanner(System.in);
        String greetings = "Добро пожаловать в чат-бот! Введите ваше сообщение: ";
        System.out.println(greetings);
        log.add(greetings);
        String question = in.nextLine();
        while (!OUT.equals(question)) {
            ioUserLog(question, log);
            if (modOn && STOP.equals(question)) {
                modOn = false;
            } else if (!modOn && CONTINUE.equals(question)) {
                ioBotLog(botAnswerList, log);
                modOn = true;
            } else if (modOn) {
                ioBotLog(botAnswerList, log);
            }
            question = in.nextLine();
        }
        ioUserLog(question, log);
        saveLog(path, log);
    }

    private void ioUserLog(String question, List<String> log) {
        String user = "Пользователь: " + question;
        System.out.println(user);
        log.add(user);
    }

    private void ioBotLog(List<String> botAnswerList, List<String> log) {
        String bot = "Бот: " + botAnswerList.get((int) (Math.random() * botAnswerList.size()));
        System.out.println(bot);
        log.add(bot);
    }

    private List<String> readPhrases(String botAnswer) {
       List<String> answers = new ArrayList<>();
       try (BufferedReader br = new BufferedReader(
               new FileReader(botAnswer))) {
           answers = br.lines().collect(Collectors.toList());
       } catch (IOException e) {
           e.printStackTrace();
       }
       return answers;
    }

    private void saveLog(String path, List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File log = new File("./data/log.txt");
        ConsoleChat cc = new ConsoleChat(log.getAbsolutePath(), "./data/botAnswer.txt");
        cc.run();
    }
}
