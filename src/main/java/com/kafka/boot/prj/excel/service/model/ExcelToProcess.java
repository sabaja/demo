package com.kafka.boot.prj.excel.service.model;

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

    private ExcelToProcess() {
        /* Use Builder to create ExcellToProcess */}

    @Override
    public String toString() {
        return "ExcelToProcess [workBook=" + workBook + ", sheet=" + sheet + ", row=" + row + ", cell=" + cell
                + ", fineNumber=" + fineNumber + ", ogg=" + ogg + "]";
    }

    public ExcelToProcess(Builder builder) {
        this.sheet = builder.sheet;
        this.row = builder.row;
        this.cell = builder.cell;
        this.fineNumber = builder.fineNumber;
        this.ogg = builder.ogg;
    }

    public  class Builder {

        private HSSFWorkbook workBook;
        private Sheet sheet;
        private Row row;
        private Cell cell;
        private String fineNumber;
        private String ogg;

        public Builder(HSSFWorkbook workBook) {
            super();
            this.workBook = workBook;
        }

        public void set_sheet(Sheet sheet) {
            this.sheet = sheet;
        }

        public void set_row(Row row) {
            this.row = row;
        }

        public void set_cell(Cell cell) {
            this.cell = cell;
        }

        public void set_fineNumber(String fineNumber) {
            this.fineNumber = fineNumber;
        }

        public void set_ogg(String ogg) {
            this.ogg = ogg;
        }

        public ExcelToProcess build() {
            return new ExcelToProcess(this);
        }

    }

}
