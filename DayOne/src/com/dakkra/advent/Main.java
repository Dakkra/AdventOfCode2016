package com.dakkra.advent;

import java.io.InputStream;
import java.util.ArrayList;

public class Main {

    private static InputStream inputStream;
    private static ArrayList<String> inputCommands;
    private static Direction currentDirection = Direction.North;
    private static int xDelta = 0;
    private static int yDelta = 0;

    public static void main(String[] args) {
        //Init ArrayList buffer and input stream
        inputCommands = new ArrayList<>();
        inputStream = Main.class.getResourceAsStream("input");

        try {
            boolean doneReading = false;
            String elemBuffer = "";
            while (!doneReading) {
                int input = inputStream.read();
                if (input == -1) {
                    doneReading = true;
                    inputCommands.add(elemBuffer);
                }
                char charBuffer = (char) input;
                switch (charBuffer) {
                    case ',': {
                        inputCommands.add(elemBuffer);
                        elemBuffer = "";
                        break;
                    }
                    case ' ': {
                        break;
                    }
                    default: {
                        elemBuffer += charBuffer;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        processCommands();
        System.out.println(getDistance());
    }

    private static void processCommands() {
        for (String command : inputCommands) {
            int magnitude = Integer.parseInt(command.substring(1));
            switch (command.charAt(0)) {
                case 'R': {
                    turnRight();
                    move(magnitude);
                    break;
                }
                case 'L': {
                    turnLeft();
                    move(magnitude);
                    break;
                }
                default:
                    break;
            }
        }
    }

    private static int getDistance() {
        return Math.abs(xDelta) + Math.abs(yDelta);
    }

    private static void move(int magnitude) {
        switch (currentDirection) {
            case North: {
                yDelta += magnitude;
                break;
            }
            case South: {
                yDelta -= magnitude;
                break;
            }
            case East: {
                xDelta += magnitude;
                break;
            }
            case West: {
                xDelta -= magnitude;
                break;
            }
        }
    }

    private static void turnRight() {
        switch (currentDirection) {
            case North: {
                currentDirection = Direction.East;
                break;
            }
            case South: {
                currentDirection = Direction.West;
                break;
            }
            case East: {
                currentDirection = Direction.South;
                break;
            }
            case West: {
                currentDirection = Direction.North;
                break;
            }
        }

    }

    private static void turnLeft() {
        switch (currentDirection) {
            case North: {
                currentDirection = Direction.West;
                break;
            }
            case South: {
                currentDirection = Direction.East;
                break;
            }
            case East: {
                currentDirection = Direction.North;
                break;
            }
            case West: {
                currentDirection = Direction.South;
                break;
            }
        }
    }

    private enum Direction {
        North, South, East, West
    }
}
