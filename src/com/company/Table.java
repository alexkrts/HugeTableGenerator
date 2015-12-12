package com.company;


import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Table {

    private Map<Cell, String> tableMap = new HashMap<>();
    private int baseColumnsCount;
    private int baseRowsCount;
    private int newTableColumnsCount;
    private int newTableRowsCount;
    private KeyProcessor keyProcessor = new KeyProcessor();
    public static final String EXPRESSION_START = "=";


    public void readCells() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String row;
            int currentRow = 0;
            int currentColumn = 0;
            while ((row = reader.readLine()) != null) {
                String cellsArray[] = row.split("\t");
                if (cellsArray.length > baseColumnsCount) {
                    baseColumnsCount = cellsArray.length;
                }
                baseRowsCount = currentRow;

                for (String valuePos : cellsArray) {
                    Cell cell = new Cell(currentColumn, currentRow);
                    tableMap.put(cell, valuePos);
                    currentColumn++;
                }
                currentColumn = 0;
                currentRow++;
                baseRowsCount = currentRow;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public void writeGeneratedTableToFile(int widthMultiplier, int heightMultiplier) {
        int columnPositionInBaseTable;
        int rowPositionInBaseTable;
        int shiftColumnIndex;
        int shiftRowIndex;
        newTableColumnsCount = baseColumnsCount * widthMultiplier;
        newTableRowsCount = baseRowsCount * heightMultiplier;

        StringBuilder columnLine = new StringBuilder();
        for (int i = 0; i < newTableRowsCount; i++) {
            rowPositionInBaseTable = i % baseRowsCount;
            shiftRowIndex = i - rowPositionInBaseTable;
            columnLine.setLength(0);
            for (int j = 0; j < newTableColumnsCount; j++) {
                columnPositionInBaseTable = j % baseColumnsCount;
                shiftColumnIndex = j - columnPositionInBaseTable;
                Cell cell = new Cell(columnPositionInBaseTable, rowPositionInBaseTable);
                String value = tableMap.get(cell);
                if (value != null && value.startsWith(EXPRESSION_START) && value.matches(".*[A-Z]+[0-9]+.*")) {
                    value = keyProcessor.changeExpressionOperandsAddresses(shiftColumnIndex, shiftRowIndex, value);
                } else if (value == null) {
                    value = "";
                }
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }


}
