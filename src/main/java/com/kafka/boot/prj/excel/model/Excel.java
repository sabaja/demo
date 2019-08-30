package com.kafka.boot.prj.excel.model;

import com.kafka.boot.prj.excel.model.process.IOExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public abstract class Excel {

    private HSSFWorkbook workBook;
    private IOExcel ioExcel;

    public Excel() {
        this.workBook = null;
    }

    public Excel(IOExcel ioExcel) {
        this.ioExcel = ioExcel;
    }

    public Excel(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    public HSSFWorkbook getWorkBook() {
        return workBook;
    }

    public void setWorkBook(HSSFWorkbook workBook) {
        this.workBook = workBook;
    }

    public IOExcel getIoExcel() {
        return ioExcel;
    }

    public void setIoExcel(IOExcel ioExcel) {
        this.ioExcel = ioExcel;
    }

    @Override
    public String toString() {
        return "Excel{" +
                "workBook=" + workBook +
                ", ioExcel=" + ioExcel +
                '}';
    }
}
