package com.company;


public class Cell {
    private int rowPosition;
    private int columnPosition;

    public Cell(int columnPosition, int rowPosition) {

        this.rowPosition = rowPosition;
        this.columnPosition = columnPosition;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (rowPosition != cell.rowPosition) return false;
        return columnPosition == cell.columnPosition;

    }

    @Override
    public int hashCode() {
        int result = rowPosition;
        result = 31 * result + columnPosition;
        return result;
    }
}
