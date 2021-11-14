package com.onpy;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class BinaryDataSaver {
    private static FileOutputStream outFile;
    private static FileInputStream inFile;
    private Object triangle;

    public static void save(ArrayList<Triangle> triangles, String fileWay) throws IOException {

        byte[] bytesToWrite;
        byte[] numberTriangle, x1, x2, x3, perimeter, alpha, betta, gamma, square, razd;
        String razdel = "&";

        for (Triangle object : triangles) {

            // номер треугольника
            String s1 = Double.toHexString(object.getNumTriangle());
            numberTriangle = s1.getBytes(StandardCharsets.UTF_8);

            // Сторона  №1 треугольника
            String s2 = Double.toHexString(object.getX1()); // todo get hex value from int (P.S. Speranskyy) =)
            x1 = s2.getBytes(StandardCharsets.UTF_8);

            // Сторона №2 треугольника
            String s3 = Double.toHexString(object.getX2());
            x2 = s3.getBytes(StandardCharsets.UTF_8);

            // Сторона №3 треугольника
            String s4 = Double.toHexString(object.getX3());
            x3 = s4.getBytes(StandardCharsets.UTF_8);

            // разделитель
            razd = razdel.getBytes(StandardCharsets.UTF_8);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            outputStream.write(numberTriangle);
            outputStream.write(razd);
            outputStream.write(x1);
            outputStream.write(razd);
            outputStream.write(x2);
            outputStream.write(razd);
            outputStream.write(x3);

            bytesToWrite = outputStream.toByteArray();
            outFile = null;
            boolean isOpened = false;
            try {
                outFile = new FileOutputStream(fileWay, true);
                isOpened = true;
                outFile.write(bytesToWrite); // запись в файл
            } catch (FileNotFoundException e) {
                System.out.println("Невозможно произвести запись в файл:" + fileWay);
            } catch (IOException e) {
                System.out.println("Ошибка ввода/вывода:" + e);
            }
            if (isOpened) {
                outFile.close();
            }
        }
    }

    public static void load(ArrayList<Triangle> triangles, String fileWay) throws IOException {
        triangles.clear();
        byte[] wert = new byte[0];
        int amount = 0;
        try {

            inFile = new FileInputStream(fileWay);
            int bytesAvailable = inFile.available(); //сколько можно считать
            System.out.println("Available: " + bytesAvailable);

            byte[] bytesReaded = new byte[bytesAvailable]; //куда считываем
            int count = inFile.read(bytesReaded, 0, bytesAvailable);

            System.out.println("Было считано байт: " + count);
            System.out.println(Arrays.toString(bytesReaded));
            byte[] trap = bytesReaded;
            wert = trap;
            amount = count;
            inFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("Невозможно произвести чтение из файла:" + fileWay);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода:" + e);
        }
        byte[] dannie = wert;
        int numberTriange = 0;
        int x1 = 0;
        int x2 = 0;
        int x3 = 0;
        int q = 0;
        String num = "";

        for (int i = 0; i < amount; i++) {

            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, "UTF-8");
                num = num + str;
                numberTriange = Integer.parseInt(num, 2);
            }

            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, "UTF-8");
                //x1 = x1 + str;
                x1 = Integer.parseInt(String.valueOf(x1), 2);
            }
            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, "UTF-8");
                //m1 = m1 + str;
                x2 = Integer.parseInt(String.valueOf(x2), 2);
            }
            for (i = q; i < amount; i++) {
                if (dannie[i] == 46) {
                    q = i + 1;
                    break;
                }
                byte[] ab = new byte[1];
                ab[0] = dannie[i];
                String str = new String(ab, "UTF-8");
                //d1 = d1 + str;
                x3 = Integer.parseInt(String.valueOf(x3), 2);
            }
            if (i == amount - 1) {
                break;
            }
            //triangles.add(new triangle(x1, x2, x3, numberTriange));
            numberTriange = 0;
            x1 = 0;
            x2 = 0;
            x3 = 0;
        }
    }
}
