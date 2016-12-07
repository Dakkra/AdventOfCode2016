package com.dakkra.advent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Room {

    private String dataLine;
    private String roomName;
    private String checksum;
    private int roomID;

    public Room(String dataLine) {
        this.dataLine = dataLine;
        parseRoomId();
        parseChecksum();
        parseRoomName();
    }

    public int getRoomID() {
        return roomID;
    }

    public String getChecksum() {
        return checksum;
    }

    public boolean isRealRoom() {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : roomName.toCharArray()) {
            //Ignore dashes
            if (c == '-')
                continue;

            if (map.containsKey(c)) {
                int val = map.get(c);
                map.replace(c, val + 1);
            } else {
                map.put(c, 1);
            }
        }

        System.out.println("REAL:" + generateKeyFromMap(map) + " CHK:" + checksum);

        return generateKeyFromMap(map).equals(checksum);
    }

    public void testRoom() {
        System.out.println("ID:" + roomID + " chk:" + checksum + " name:" + roomName);
    }

    private String generateKeyFromMap(HashMap<Character, Integer> map) {
        String buffer = "";
        //Sort map
        ArrayList<CharIndex> charIndices = new ArrayList<>();
        for (char c : map.keySet()) {
            int val = map.get(c);
            charIndices.add(new CharIndex(c, val));
        }
        Collections.sort(charIndices);
        Collections.reverse(charIndices);
        //Add to buffer
        for (int i = 0; i < 5; i++) {
            buffer += charIndices.get(i).getC();
        }
        return buffer;
    }

    private void parseRoomName() {
        String roomNameBuffer = dataLine;
        String lineEnd = parseLineEnd();
        int leSize = lineEnd.length();

        roomName = roomNameBuffer.substring(0, roomNameBuffer.length() - leSize);
    }

    private void parseChecksum() {
        String lineEnd = parseLineEnd();
        String checksumBuff = "";

        boolean addChars = false;
        for (char c : lineEnd.toCharArray()) {
            if (c == ']')
                break;
            if (addChars)
                checksumBuff += c;
            if (c == '[')
                addChars = true;
        }

        checksum = checksumBuff;
    }

    private void parseRoomId() {
        String lineEnd = parseLineEnd();
        String numBuff = "";

        for (char c : lineEnd.toCharArray()) {
            if (c == '[')
                break;
            numBuff += c;
        }

        roomID = Integer.parseInt(numBuff);
    }

    private String parseLineEnd() {
        char dataLineChars[] = dataLine.toCharArray();
        String idAndChecksumBuffer = "";

        for (int i = dataLineChars.length - 1; i >= 0; i--) {
            if (dataLineChars[i] == '-')
                break;
            idAndChecksumBuffer += dataLineChars[i];
        }
        return new StringBuilder(idAndChecksumBuffer).reverse().toString();
    }

    class CharIndex implements Comparable {
        private char c;
        private int count;

        public CharIndex(char c, int num) {
            this.c = c;
            this.count = num;
        }

        public char getC() {
            return c;
        }

        public int getCount() {
            return count;
        }


        @Override
        public int compareTo(Object o) {
            CharIndex ci = (CharIndex) o;
            int otherCount = ci.getCount();
            if (count == otherCount) {
                int mVal = c;
                int oVal = ci.getC();
                return (oVal > mVal) ? 1 : -1;
            }
            return (count > otherCount) ? 1 : -1;
        }
    }
}
