package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static List<String> commands = Arrays.asList("0", "1", "2");
    private static List<String> descriptions = Arrays.asList("выход", "изменить 1 строку", "изменить 2 строку");

    public static void main(String[] args) {
        try {
            run();
        } catch (RuntimeException e) {
            System.err.println(e.getClass() + ": " + e.getMessage());
        }
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поиск максимальной подстроки");

        State s;
        try {
            s = StateManager.getState();
        } catch (RuntimeException e) {
            s = new State();
            StateManager.setState(s);
        }

        System.out.println("Текущее состояние");
        System.out.println(s);

        StringBuilder sb = new StringBuilder("Доступные команды:\n");
        for (int i = 0; i < commands.size() && i < descriptions.size(); i++) {
            sb.append(commands.get(i)).append(" - ").append(descriptions.get(i)).append("\n");
        }
        String help = sb.toString(), command;

        while (true) {
            System.out.println(help);
            System.out.print(">>> ");
            command = readCommand(scanner);

            if ("0".equals(command)) {
                break;
            } else if ("1".equals(command)) {
                System.out.print("s1 = ");
                String s1 = readString(scanner);
                s.setS1(s1);
            } else if ("2".equals(command)) {
                System.out.print("s2 = ");
                String s2 = readString(scanner);
                s.setS2(s2);
            }
            System.out.println("Текущее состояние");
            System.out.println(s);
            StateManager.setState(s);
        }
    }

    private static String readString(Scanner scanner) {
        String s = scanner.nextLine();
        boolean b;
        while (true) {
            b = true;
            for (char c : s.toCharArray()) {
                if (c < 'a' || 'z' < c) {
                    b = false;
                    break;
                }
            }
            if (b && !s.isEmpty()) {
                return s;
            } else {
                System.out.println("Строка может состоять только из маленьких букв латинского алфавита");
                System.out.print(">>> ");
                s = scanner.nextLine();
            }
        }
    }

    private static String readCommand(Scanner scanner) {
        String s = scanner.nextLine();

        while (!commands.contains(s)) {
            System.out.println("Неверная команда");
            System.out.print(">>> ");
            s = scanner.nextLine();
        }

        return s;
    }
}
