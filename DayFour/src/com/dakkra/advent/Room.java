package com.dakkra.advent;

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
        return true;
    }

    public void testRoom() {
        System.out.println("ID:" + roomID + " chk:" + checksum + " name:" + roomName);
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
}
