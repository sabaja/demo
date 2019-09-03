package com.kafka.boot.prj.excel.service.process.constant;

public enum ExcelDataProcess {
    TC("TC"), SG("SG"), OGG("Ogg. "),
    FORMAT_PATTERN_NUM("000"), FORMAT_PATTERN_FINE("00"),
    PATH("C:\\Users\\sabatinija\\Desktop\\Testrename\\TestBook_Progettazione Giga Ricarica - Copy.xls"),
    COPY("_Copy"), SUFFIX(".xls"),
    FINE_COLUMN(2), OGG_COLUMN(4), ROW(1), SHEET(2);

    private int anInt;
    private String aString;

    private ExcelDataProcess(int anInt) {
        this.anInt = anInt;
    }

    public String getAString() {
        return aString;
    }

    private ExcelDataProcess(String aString) {
        this.aString = aString;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}


