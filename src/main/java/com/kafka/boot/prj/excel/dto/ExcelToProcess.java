package com.kafka.boot.prj.excel.dto;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

//https://codingjam.it/dai-costruttori-al-builder-pattern-in-java/
public class ExcelToProcess {
    /*
     * private  final String TC = "TC"; private  int TC_START_INDEX;
     * private  int TC_END_INDEX; private  final String SG = "SG";
     * private  final String PATTERN_FINE_NUMBER = "00"; private  final
     * String PATTERN_OGG_NUMBER = "000"; private  final int COLUMN = 2;
     * private  final int ROW = 1; private  final int SHEET = 1; private
     *  String fineNumber;
     */

    private HSSFWorkbook workBook; // mandatory
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


    public ExcelToProcess(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public Row getRow() {
        return row;
    }

    public Cell getCell() {
        return cell;
    }

    public String getFineNumber() {
        return fineNumber;
    }

    public String getOgg() {
        return ogg;
    }

    public int getColumnFineNumber() {
        return columnFineNumber;
    }

    public int getColumnOggNumber() {
        return columnOggNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSheetNumber() {
        return sheetNumber;
    }

    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void setFineNumber(String fineNumber) {
        this.fineNumber = fineNumber;
    }

    public void setOgg(String ogg) {
        this.ogg = ogg;
    }

    public void setColumnFineNumber(int columnFineNumber) {
        this.columnFineNumber = columnFineNumber;
    }

    public void setColumnOggNumber(int columnOggNumber) {
        this.columnOggNumber = columnOggNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public void setSheetNumber(int sheetNumber) {
        this.sheetNumber = sheetNumber;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ExcelToProcess{" +
                "workBook=" + workBook +
                ", sheet=" + sheet +
                ", row=" + row +
                ", cell=" + cell +
                ", fineNumber='" + fineNumber + '\'' +
                ", ogg='" + ogg + '\'' +
                ", path='" + path + '\'' +
                ", columnFineNumber=" + columnFineNumber +
                ", columnOggNumber=" + columnOggNumber +
                ", rowNumber=" + rowNumber +
                ", sheetNumber=" + sheetNumber +
                '}';
    }
}
