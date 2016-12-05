package com.dakkra.advent;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    private static LinkedList<Triangle> triangles = new LinkedList<>();
    private static InputStream inputStream;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) {
        inputStream = Main.class.getResourceAsStream("input2");
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
                System.out.println(lineBuffer);
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void assesLine(String lineBuffer) {
        int num[] = new int[3];
        int index = 0;
        String stringBuff = "";
        char charBuff;
        //TODO process this shit.
    }

    static class Triangle {
        int l, w, h;

        public Triangle(int l, int w, int h) {
            this.l = l;
            this.w = w;
            this.h = h;
        }

        public boolean isRealTriangle() {
            return (l + w) > h;
        }
    }

}
