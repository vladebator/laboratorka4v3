package com.onpy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int CheckCorrectFunction(String value) {
        int func = -1;
        try {
            func = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            func = -1;
        }
        return func;
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        int inCountTriangle = 1;
        int function = 0;
        Triangle[] triangles = new Triangle[inCountTriangle];

        do {
            System.out.println("\nСписок функций: ");
            System.out.println("[1] Ввести/Вычислить доп. данные");
            System.out.println("[2] Сохранение в файл");
            System.out.println("[3] Сериализация базы данных");
            System.out.println("[4] Десериализация базы данных");
            System.out.println("[5] Сериализация Jackson базы данных");
            System.out.println("[6] Десериализация Jackson базы данных");
            System.out.println("[9] Выход из программы");
            do {
                System.out.print("Введите номер функции: ");
                function = CheckCorrectFunction(scan.nextLine());
            } while (function == -1);
            
            switch (function) {
                case 1:
                    Triangles trianglesInput = new Triangles();
                    trianglesInput.dataInput();
                    break;
                case 2:
                    System.out.println("Введите путь к файлу:");
                    //String wayToFile = scan.nextLine();
                    ArrayList<Triangle> triangleArrayList = new ArrayList<>();
                    triangleArrayList.addAll(Arrays.asList(triangles));
                    BinaryDataSaver.save(triangleArrayList, "d:\\1.txt");
                    System.out.println("Файл успешно сохранён!");
                    break;
                case 3:
                    System.out.print("\nВведите путь для сохранения файла:");
                    String serializeFileName = scan.nextLine();
                    Triangles.serializeFile(serializeFileName);
                    break;
                case 4:
                    System.out.print("\nВведите путь к базе данных:");
                    String deserializeFileName = scan.nextLine();
                    Triangles.deserializeFile(deserializeFileName);
                    break;
                case 5:
                    System.out.print("\nВведите путь для сохранения файла:");
                    String jacks    onSerializeFileName = scan.nextLine();
                    Triangles.JacksonSerializeFile(jacksonSerializeFileName);
                    break;
                case 6:
                    System.out.print("\nВведите путь к базе данных:");
                    String jacksonDeserializeFileName = scan.nextLine();
                    Triangles.jacksonDeserializeFile(jacksonDeserializeFileName);
                    break;
                case 9:
                    return;
                default:
                    System.out.println("\nТакой функции нету");
            }
        } while (function != 0);
    }
}