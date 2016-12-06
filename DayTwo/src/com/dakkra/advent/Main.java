package com.dakkra.advent;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    private static String bathroomCode = "";
    private static InputStream inputStream;
    private static BufferedReader bufferedReader;
    private static Point fingerLocation = new Point(2, 2);
    private static final int MAX_LOCATION = 3;
    private static final int MIN_LOCATION = 1;
    /*
       1 2 3
    1 |1|2|3|
    2 |4|5|6|
    3 |7|8|9|

    2,2 is the center
     */

    public static void main(String[] args) {
        inputStream = Main.class.getResourceAsStream("input");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            boolean doneReading = false;
            while (!doneReading) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    processLine(line);
                    bathroomCode += Key.getKeyAtCoord(fingerLocation).getVal();
                } else
                    doneReading = true;

            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(bathroomCode);
    }

    private static void processLine(String line) {
        for (char c : line.toCharArray()) {
            switch (c) {
                case 'L': {
                    moveLeft();
                    break;
                }
                case 'R': {
                    moveRight();
                    break;
                }
                case 'U': {
                    moveUp();
                    break;
                }
                case 'D': {
                    moveDown();
                    break;
                }
                default:
                    break;
            }
        }
    }

    private static void moveLeft() {
        fingerLocation = new Point(Math.max(fingerLocation.x - 1, MIN_LOCATION), fingerLocation.y);
    }

    private static void moveRight() {
        fingerLocation = new Point(Math.min(fingerLocation.x + 1, MAX_LOCATION), fingerLocation.y);
    }

    private static void moveUp() {
        fingerLocation = new Point(fingerLocation.x, Math.min(fingerLocation.y + 1, MAX_LOCATION));
    }

    private static void moveDown() {
        fingerLocation = new Point(fingerLocation.x, Math.max(fingerLocation.y - 1, MIN_LOCATION));
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
