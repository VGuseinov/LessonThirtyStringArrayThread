package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Test test = new Test();
        test.start();
        test.join(100); // waiting end Thread
        System.out.println("Here");

        // Работа со строками
        String s = "hello";
        for (char c : s.toCharArray()) {
            System.out.println((int) c);
        }
        System.out.println(toUpperCase("B ar!si} k"));
        System.out.println();

        // алгоритмы + массивы
        int[] arr = {134, 2, 8, 59, 76, 4, 10};
        sort(arr);
        for (int e : arr) {
            System.out.println(e);
        }
        System.out.println();

        // работа со строками + потоки
        FileWriter fileWriter = new FileWriter("1.txt");
        Thread thread = new Thread(() -> {
            try {
                synchronized (fileWriter) {
                    fileWriter.write("hello ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread thread1 = new Thread(() -> {
            try {
                synchronized (fileWriter) {
                    fileWriter.write("world");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        fileWriter.flush();
        fileWriter.close();
        FileReader fileReader = new FileReader("1.txt");
        char[] data = new char[5];
        fileReader.read(data);
        fileReader.close();
        String s1 = new String(data);
        System.out.println(new String(data));
        System.out.println(s1);
        char first = s1.charAt(0);
        char last = s1.charAt(s1.length() - 1);
        System.out.println(first + " " + last);
        Random random = new Random();
        System.out.println(random.nextInt(10));
    }
    // работа со строками
    public static String toUpperCase(String s) {
        int offset = 'a' - 'A';
        StringBuilder stringBuilder = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                c -= offset;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();

    }
    // алгоритмы и массивы
    public static void sort(int[] toSort) {
        for (int i = 0; i < toSort.length - 1; i++) {
            for (int j = i + 1; j < toSort.length; j++) {
                if (toSort[i] > toSort[j]) {
                    int tmp = toSort[i];
                    toSort[i] = toSort[j];
                    toSort[j] = tmp;
                }
            }
        }
    }


}
