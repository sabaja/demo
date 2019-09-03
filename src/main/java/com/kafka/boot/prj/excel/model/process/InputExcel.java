package com.kafka.boot.prj.excel.model.process;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

//https://codingjam.it/dai-costruttori-al-builder-pattern-in-java/
public class InputExcel {

    private IOExcel ioExcel;
    private Sheet sheet;
    private Row row;
    private Cell cell;
    private String fineNumber;
    private String ogg;
    private String path;
    private int columnFineNumber;
    private int columnOggNumber;
    private int rowNumber;
    private int sheetNumber;


    public IOExcel getIoExcel() {
        return ioExcel;
    }

    public void setIoExcel(IOExcel ioExcel) {
        this.ioExcel = ioExcel;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public String getFineNumber() {
        return fineNumber;
    }

    public void setFineNumber(String fineNumber) {
        this.fineNumber = fineNumber;
    }

    public String getOgg() {
        return ogg;
    }

    public void setOgg(String ogg) {
        this.ogg = ogg;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getColumnFineNumber() {
        return columnFineNumber;
    }

    public void setColumnFineNumber(int columnFineNumber) {
        this.columnFineNumber = columnFineNumber;
    }

    public int getColumnOggNumber() {
        return columnOggNumber;
    }

    public void setColumnOggNumber(int columnOggNumber) {
        this.columnOggNumber = columnOggNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    @Override
    public String toString() {
        return "InputExcel{" +
                " sheet:" + sheet +
                ", row:" + row +
                ", cell:" + cell +
                ", fineNumber:'" + fineNumber + '\'' +
                ", ogg:'" + ogg + '\'' +
                ", path:'" + path + '\'' +
                ", columnFineNumber:" + columnFineNumber +
                ", columnOggNumber:" + columnOggNumber +
                ", rowNumber:" + rowNumber +
                ", sheetNumber:" + sheetNumber +
                '}';
    }
}
