/*
Создать класс треугольник, члены класса – длины 3-х сторон. Предусмотреть в классе методы
вычисления и вывода сведений о фигуре – длины сторон, углы, периметр, площадь. Создать
производный класс – равнобедренный треугольник, предусмотреть в классе проверку, является
ли треугольник равнобедренным. Написать программу, демонстрирующую работу с классом:
дано N треугольников и M равнобедренных треугольников, найти среднюю площадь для N
треугольников и равнобедренный треугольник с наименьшей площадью.
*/

package com.onpy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int inCountTriangle = 1;//scan.nextInt();
        System.out.println("Введите количество треугольников: " + inCountTriangle);

        triangle[] triangles = new triangle[inCountTriangle];

        int CountTriangle = 0;

        for (int i = 0; i < triangles.length; i++) {

            triangles[i] = new triangle();

            System.out.print("Введите cторону Х1 для треугольника №" + (i + 1) + ": ");
            triangles[i].x1 = scan.nextInt();
            System.out.print("Введите cторону Х2 для треугольника №" + (i + 1) + ": ");
            triangles[i].x2 = scan.nextInt();
            System.out.print("Введите cторону Х3 для треугольника №" + (i + 1) + ": ");
            triangles[i].x3 = scan.nextInt();

            //x1 = 1 + Math.random() * 10;
            //x2 = 1 + Math.random() * 10;
            //x3 = 1 + Math.random() * 10;

            triangles[i].numberTriangle = ++CountTriangle;
        }

        for (int i = 0; i < triangles.length; i++) {
            triangles[i].perimeter = triangles[i].x1 + triangles[i].x2 + triangles[i].x3;
            System.out.println("Периметр треугольника №" + triangles[i].numberTriangle + ": " + triangles[i].perimeter);

            triangles[i].alpha = Math.abs(Math.cos(((pow(triangles[i].x1, 2) + pow(triangles[i].x3, 2) - pow(triangles[i].x2, 2)) / 2 * triangles[i].x1 * triangles[i].x3)));
            triangles[i].betta = Math.abs(Math.cos(((pow(triangles[i].x1, 2) + pow(triangles[i].x2, 2) - pow(triangles[i].x3, 2)) / 2 * triangles[i].x1 * triangles[i].x2)));
            triangles[i].gamma = Math.abs(Math.cos(((pow(triangles[i].x2, 2) + pow(triangles[i].x3, 2) - pow(triangles[i].x1, 2)) / 2 * triangles[i].x3 * triangles[i].x2)));

            double radian = 57.295780;

            System.out.println("Косинус угла Alpha = " + triangles[i].alpha);
            System.out.println("Косинус угла Betta = " + triangles[i].betta);
            System.out.println("Косинус угла Gamma = " + triangles[i].gamma);

            triangles[i].square = 0.5 * triangles[i].x1 * triangles[i].x2 * Math.sin(triangles[i].alpha);
            System.out.println("Площадь треугольника №" + triangles[i].numberTriangle + " = " + triangles[i].square);

            if (triangles[i].x1 == triangles[i].x2 && triangles[i].x1 == triangles[i].x3 && triangles[i].x2 == triangles[i].x3) {
                System.out.println("Треугольник №" + triangles[i].numberTriangle + " является равнобедренным.");
                triangles[i].isosceles = 1;
            }
            else {
                System.out.println("Треугольник №" + triangles[i].numberTriangle + " не является равнобедренным.");
            }
        }

        double averageSquare = 0;
        double max = 0;
        double min = 0;
        int countNormalTriangle = 0;
        int countIsoscelesTriangle = 0;

        // И в этом цикле я вычисляю среднюю площадь для обычных треугольников
        for (int i = 0; i < triangles.length; i++) {
            if(triangles[i].isosceles == 0) {
                countNormalTriangle = countNormalTriangle + 1;
                averageSquare = +triangles[i].square;
            }
        }

        // В этом цикле находим минимальную площадь равнобедренных треугольников
        for (int i = 0; i < triangles.length; i++) {
            if (triangles[i].isosceles == 1) {
                countIsoscelesTriangle = countIsoscelesTriangle + 1;
                for (int j = 0; j != triangles.length; j++) {
                    if (!(triangles[i].square < max)) {
                        max = triangles[i].square;
                    }
                    if (triangles[i].square < min) {
                        min = triangles[i].square;
                    }
                }
            }
        }
        averageSquare = averageSquare / countNormalTriangle;
        // Выводим теперь эти данные на экран, среднюю площадь треугольников и мин.площ. равнобедренного треугольника.
        System.out.println("Средняя площадь треугольников = " + averageSquare);
        System.out.println("Минимальная площадь равнобедренного треугольника = " + min);

        // Запись строковых данных в файл
        long timeStart = System.currentTimeMillis();
        String fileName4 = "D:\\file4laba.txt";
        FileWriter fw;
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        // Строка, которая будет записана в файл
        for (int i = 0; i < triangles.length; i++) {
            String size1 = "Сторонa Х1 треугольника №" + (i+1) + ": " + triangles[i].x1 + "\n";
            String size2 = "Сторонa Х2 треугольника №" + (i+1) + ": " + triangles[i].x2 + "\n";
            String size3 = "Сторонa Х3 треугольника №" + (i+1) + ": " + triangles[i].x3 + "\n";
            String size4 = "Периметр треугольника №" + (i+1) + ": " + triangles[i].perimeter + "\n";
            String size5 = "Косинус угла Alpha треугольника №" + (i+1) + " = " + triangles[i].alpha + "\n";
            String size6 = "Косинус угла Betta треугольника №" + (i+1) + " = " + triangles[i].betta + "\n";
            String size7 = "Косинус угла Gamma треугольника №" + (i+1) + " = " + triangles[i].gamma + "\n";
            String size8 = "Площадь треугольника №" + (i+1) + " = " + triangles[i].square + "\n";
            try {
                fw = new FileWriter(fileName4);
                bw = new BufferedWriter(fw);
                System.out.println("Write some data to file: " + fileName4);
                // Несколько раз записать строку
                for (int j = 1; --j >= 0; ) {
                    bw.write(size1);
                    bw.write(size2);
                    bw.write(size3);
                    bw.write(size4);
                    bw.write(size5);
                    bw.write(size6);
                    bw.write(size7);
                    bw.write(size8);
                }
                bw.close();

                fr = new FileReader(fileName4);
                br = new BufferedReader(fr);
                String s;
                int count = 0;
                System.out.println("Read  data from file: " + fileName4);
                // Считывать данные, отображая на экран
                while ((s = br.readLine()) != null) ;
                // System.out.println("row  " + ++count + " read:" + s);
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(System.currentTimeMillis() - timeStart);
            }
        }
    }
}