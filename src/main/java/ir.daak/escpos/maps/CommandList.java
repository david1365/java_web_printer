package ir.daak.escpos.maps;

import ir.daak.escpos.model.Command;

import java.util.HashMap;

public class CommandList extends HashMap<String, Command> {
    public static final byte[] initialize =  { 0x1b, 0x40}; //Initialize printer\

    public static final byte[] eject =  { 0x0c }; //Print and eject slip paper

    public static final byte[] slipInsertionSensor = {0x1B, 0x63, 0x34 , 0};//Select paper sensor(s) to stop printing

    public CommandList() {
        super();

        put("FEED", new Command( new byte[] {0x0A})); // Print and line feed
        put("FEED_LINES", new Command( new byte[] {0x1B, 0x64, 0}, true )); // Print and line feed
        put("SLIP_INSERTION_SENSOR", new Command( new byte[] {0x1B, 0x63, 0x34 , 0}, true));//Select paper sensor(s) to stop printing

        put("CAN_HT", new Command( new byte[] {0x1b, 0x44, 0x00} )); // Cancel  Horizontal Tab
        put("HT", new Command( new byte[] {0x09} )); // Horizontal Tab
        put("LINE_SPACE_24", new Command( new byte[] {0x1b, 0x33, 24} )); // Set the line spacing at 24
        put("LINE_SPACE_30", new Command( new byte[] {0x1b, 0x33, 30} )); // Set the line spacing at 30
        //Image
        put("SELECT_BIT_IMAGE_MODE", new Command( new byte[] {0x1B, 0x2A, 33} ));
        // Printer hardware
        put("HW_INIT", new Command( new byte[] {0x1b, 0x40} )); // Clear data in buffer and reset modes
        // Cash Drawer
        put("CD_KICK_2", new Command( new byte[] {0x1b, 0x70, 0x00} )); // Sends a pulse to pin 2 []
        put("CD_KICK_5", new Command( new byte[] {0x1b, 0x70, 0x01} )); // Sends a pulse to pin 5 []
        // Paper
        put("PAPER_FULL_CUT", new Command( new byte[] {0x1d, 0x56, 0x00} )); // Full cut paper
        put("PAPER_PART_CUT", new Command( new byte[] {0x1d, 0x56, 0x01} )); // Partial cut paper
        // Text format
        put("TXT_NORMAL", new Command( new byte[] {0x1b, 0x21, 0x00} )); // Normal text
        put("TXT_2HEIGHT", new Command( new byte[] {0x1b, 0x21, 0x10} )); // Double height text
        put("TXT_2WIDTH", new Command( new byte[] {0x1b, 0x21, 0x20} )); // Double width text
        put("TXT_4SQUARE", new Command( new byte[] {0x1b, 0x21, 0x30} )); // Quad area text
        put("TXT_UNDERL_OFF", new Command( new byte[] {0x1b, 0x2d, 0x00} )); // Underline font OFF
        put("TXT_UNDERL_ON", new Command( new byte[] {0x1b, 0x2d, 0x01} )); // Underline font 1-dot ON
        put("TXT_UNDERL2_ON", new Command( new byte[] {0x1b, 0x2d, 0x02} )); // Underline font 2-dot ON
        put("TXT_BOLD_OFF", new Command( new byte[] {0x1b, 0x45, 0x00} )); // Bold font OFF
        put("TXT_BOLD_ON", new Command( new byte[] {0x1b, 0x45, 0x01} )); // Bold font ON
        put("TXT_FONT_A", new Command( new byte[] {0x1b, 0x4d, 0x00} )); // Font type A
        put("TXT_FONT_B", new Command( new byte[] {0x1b, 0x4d, 0x01} )); // Font type B

        // Align
        put("ALIGN_LEFT", new Command( new byte[] {0x1B, 0x61, 0x00} )); // Left justification
        put("ALIGN_CENTER", new Command( new byte[] {0x1B, 0x61, 0x01} )); // Centering
        put("ALIGN_RIGHT", new Command( new byte[] {0x1B, 0x61, 0x02} )); // Right justification

        put("LEFT_MARGIN", new Command( new byte[] {0x1B, 0x6c, 0x08} )); // Left Margin

        // Direction
        put("DIRECTION_RIGHT_TO_LEFT", new Command(true, true )); // Right to left Direction
        put("DIRECTION_LEFT_TO_RIGHT", new Command( true, true )); // Left to right Direction

        put("TEXT", new Command( true )); // TEXT
    }
}


