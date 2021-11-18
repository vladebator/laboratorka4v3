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
            System.out.println("[3] Выход из программы");
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
                    return;
                default:
                    System.out.println("\nТакой функции нету");
            }
        } while (function != 0);
    }
}