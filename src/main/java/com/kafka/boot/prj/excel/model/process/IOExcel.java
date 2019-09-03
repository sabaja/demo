package com.kafka.boot.prj.excel.model.process;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class IOExcel {

    private final String path;
    private boolean processed;
    private HSSFWorkbook workBook;

    public IOExcel(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }
}
