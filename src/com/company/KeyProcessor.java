package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KeyProcessor {
    private List<Integer> keyList;
    private StringBuilder stringKey;
    private static final int ALPHABET_START_ASCII = 65;
    private static final int ALPHABET_SIZE = 26;
    public static final String CELL_ADDRESS_PATTERN = "[A-Z]+[0-9]+";


    public int convertKeyToInt(String stringKey) {
        int intKey = 0;
        char[] keyArray = stringKey.toCharArray();
        int letter;
        for (int letterPosition = 1; letterPosition <= keyArray.length; letterPosition++) {
            letter = keyArray[letterPosition - 1] - ALPHABET_START_ASCII;
            intKey = (int) (intKey + Math.pow(ALPHABET_SIZE, keyArray.length - letterPosition) * letter);


        }
        return intKey;
    }

    public String convertKeyToString(int intKey) {
        keyList = new LinkedList<>();
        stringKey = new StringBuilder();
        calculateColumnKey(intKey);
        generateLetterKey();
        return stringKey.toString();

    }

    private void calculateColumnKey(int key) {
        int result;
        result = key % ALPHABET_SIZE;
        keyList.add(0, result);

        if (key > 25) {
            key = key / ALPHABET_SIZE;
            calculateColumnKey(key);

        }

    }

    private void generateLetterKey() {
        int intKey;
        for (Integer i : keyList) {
            intKey = i + ALPHABET_START_ASCII;
            char letter = (char) intKey;
            stringKey.append(letter);

        }

    }

    public String changeExpressionOperandsAddresses(int columnsShift, int rowsShift, String expression) {

        expression = expression.substring(1).trim();
        Matcher m = Pattern.compile(CELL_ADDRESS_PATTERN).matcher(expression);
        String newExpression = "";
        int previousExpEnd = 0;
        String processedOperand;
        while (m.find()) {
            processedOperand = processOperand(columnsShift, rowsShift, m.group());
            newExpression = newExpression + expression.substring(previousExpEnd, m.start()) + processedOperand;
            previousExpEnd = m.end();
        }
        newExpression = newExpression + expression.substring(previousExpEnd);


        return "=" + newExpression;
    }

    public String processOperand(int columnsShift, int rowsShift, String operand) {
        String columnKeyPart;
        String rowKeyPart;
        columnKeyPart = operand.replaceAll("[0-9]", "").trim();
        int intColumnPosition = convertKeyToInt(columnKeyPart);
        columnKeyPart = convertKeyToString(intColumnPosition + columnsShift);
        rowKeyPart = operand.replaceAll("[A-Z]", "").trim();
        int intRowKeyPart = Integer.parseInt(rowKeyPart) + rowsShift;
        operand = columnKeyPart + intRowKeyPart;


        return operand;
    }


}
