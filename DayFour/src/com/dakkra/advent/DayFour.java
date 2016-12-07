package com.dakkra.advent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class DayFour {

    private static LinkedList<String> lines;
    private static LinkedList<Room> rooms;
    private static int sumOfRealRooms = 0;
    private static InputStream inputStream;
    private static BufferedReader bufferedReader;

    public static void main(String[] args) {
        lines = new LinkedList<>();
        rooms = new LinkedList<>();
        inputStream = DayFour.class.getResourceAsStream("input");
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            boolean endOfFile = false;
            while (!endOfFile) {
                String lineBuffer = bufferedReader.readLine();
                if (lineBuffer != null)
                    lines.add(lineBuffer);
                else
                    endOfFile = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (String s : lines)
            rooms.add(new Room(s));

        for (Room r : rooms) {
            if (r.isRealRoom())
                sumOfRealRooms += r.getRoomID();
        }

        System.out.println(sumOfRealRooms);
    }
}
