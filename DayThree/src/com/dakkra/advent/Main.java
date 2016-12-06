package com.dakkra.advent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        int num[] = new int[3];
        int numIndex = 0;
        int charIndex = 0;
        char charBuff;
        char lineBufChars[] = lineBuffer.toCharArray();
        String stringBuff = "";

        while (numIndex <= 2) {
            while ((charBuff = lineBufChars[charIndex++]) == ' ' && charIndex < lineBufChars.length) {
                //literally do nothing here...
            }
            stringBuff += charBuff;
            if (charIndex < lineBufChars.length)
                while ((charBuff = lineBufChars[charIndex++]) != ' ' && charIndex < lineBufChars.length) {
                    stringBuff += charBuff;
                }
            num[numIndex] = Integer.parseInt(stringBuff);
            numIndex++;
            stringBuff = "";
        }
        triangles.add(new Triangle(num[0], num[1], num[2]));
    }

    static class Triangle {
        int l, w, h;

        public Triangle(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }

        public boolean isRealTriangle() {
            boolean testOne, testTwo, testThree;
            testOne = (l + w) >= h;
            testTwo = (l + h) >= w;
            testThree = (w + h) >= l;
            return (testOne && testTwo && testThree);
        }
    }

}
