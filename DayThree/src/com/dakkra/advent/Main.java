package com.dakkra.advent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    private static LinkedList<Triangle> triangles = new LinkedList<>();
    private static InputStream inputStream;
    private static BufferedReader bufferedReader;
    private static int realTriangleCount = 0;

    public static void main(String[] args) {
        inputStream = Main.class.getResourceAsStream("input");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            boolean doneReading = false;
            while (!doneReading) {
                String lineBuffer = bufferedReader.readLine();
                if (lineBuffer == null) {
                    doneReading = true;
                    continue;
                }
                assesLine(lineBuffer);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Num triangle: " + triangles.size());

        for (Triangle t : triangles) {
            if (t.isRealTriangle())
                realTriangleCount++;
        }
        System.out.println("Real triangle: " + realTriangleCount);
    }

    private static void assesLine(String lineBuffer) {
        String numbersStrings[] = lineBuffer.split("\\s+");
        int numbers[] = new int[3];
        int numbersIndex = 0;
        for (String s : numbersStrings) {
            if (!s.equals(""))
                numbers[numbersIndex++] = Integer.parseInt(s);
        }
        Arrays.sort(numbers);
        triangles.add(new Triangle(numbers[2], numbers[1], numbers[0]));
    }

    static class Triangle {
        int largest, w, l;

        public Triangle(int largest, int w, int l) {
            this.largest = largest;
            this.w = w;
            this.l = l;
        }

        public boolean isRealTriangle() {
            return (w + l) > largest;
        }

        @Override
        public String toString() {
            return "L: " + largest + " W: " + w + " H: " + l;
        }
    }

}
