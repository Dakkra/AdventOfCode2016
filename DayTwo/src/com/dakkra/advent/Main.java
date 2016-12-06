package com.dakkra.advent;

import jdk.internal.util.xml.impl.Input;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    private static String bathroomCode = "";
    private static InputStream inputStream;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) {
        inputStream = Main.class.getResourceAsStream("input");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

    }

    private enum Key {
        ONE(1, 1, 1), TWO(2, 2, 1), THREE(3, 3, 1), FOUR(4, 1, 2), FIVE(5, 2, 2), SIX(6, 3, 2), SEVEN(7, 1, 3), EIGHT(8, 2, 3), NINE(9, 3, 3);

        int val, column, row;

        Key(int val, int column, int row) {
            this.val = val;
            this.column = column;
            this.row = row;
        }

        public int getVal() {
            return val;
        }

        public Point getPosition() {
            return new Point(column, row);
        }

        public static Key getKeyAtCoord(Point p) {
            int c = p.x;
            int r = p.y;
            for (Key k : values()) {
                if (k.getPosition().equals(p)) {
                    return k;
                }
            }
            System.err.println("BAD COORDINATE");
            return null;
        }
    }

}
