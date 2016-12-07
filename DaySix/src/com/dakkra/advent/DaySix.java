package com.dakkra.advent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DaySix {

    private static InputStream inputStream;
    private static BufferedReader bufferedReader;
    private static ArrayList<String> lines;
    private static ArrayList<Column> columns;
    private static int lineLen = 0;
    private static boolean arrayInit = false;

    public static void main(String[] args) {
        inputStream = DaySix.class.getResourceAsStream("input");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        lines = new ArrayList<>();
        columns = new ArrayList<>();

        try {
            boolean endOfFile = false;
            while (!endOfFile) {
                String lineBuffer = bufferedReader.readLine();
                if (lineBuffer == null)
                    endOfFile = true;
                else
                    lines.add(lineBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : lines) {
            char lineChars[] = s.toCharArray();

            if (!arrayInit) {
                lineLen = lineChars.length;
                columns = new ArrayList<>();

                for (int i = 0; i < lineLen; i++) {
                    columns.add(new Column());
                }
                arrayInit = true;
            }

            //Add chars to columns
            for (int i = 0; i < lineLen; i++) {
                columns.get(i).addChar(lineChars[i]);
            }
        }

        String result = "";
        for (Column c : columns) {
            result += c.getCommonChar();
        }

        System.out.println("Result: " + result);
    }

    static class Column {
        private HashMap<Character, Integer> hashMap = new HashMap<>();

        public void addChar(char c) {
            if (hashMap.containsKey(c)) {
                int val = hashMap.get(c);
                hashMap.replace(c, val + 1);
            } else {
                hashMap.put(c, 1);
            }
        }

        public char getCommonChar() {
            ArrayList<CharCount> charIndicies = new ArrayList<>();
            for (char c : hashMap.keySet()) {
                int val = hashMap.get(c);
                charIndicies.add(new CharCount(c, val));
            }

            Collections.sort(charIndicies);
            Collections.reverse(charIndicies);
            return charIndicies.get(0).getChar();
        }
    }

    static class CharCount implements Comparable {
        private char c;
        private int count;

        public CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public char getChar() {
            return c;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(Object o) {
            CharCount cc = (CharCount) o;
            int otherCount = cc.getCount();
            return (count > otherCount) ? 1 : -1;
        }
    }

}
