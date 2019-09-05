package com.kafka.boot.prj.excel.model.process;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.time.Instant;

public class IOExcel {

    private final String sourcePath;
    private String destinationPath;
    private boolean processed;
    private Instant processedTime;
    private HSSFWorkbook workBook;

    public IOExcel(String sourcePath, String destinationPath) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    public String getSourcePath() {
        return sourcePath;
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

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public Instant getProcessedTime() {
        return processedTime;
    }

    public void setProcessedTime(Instant processedTime) {
        this.processedTime = processedTime;
    }

    @Override
    public String toString() {
        return "IOExcel{" +
                "sourcePath='" + sourcePath + '\'' +
                ", destinationPath='" + destinationPath + '\'' +
                ", processed=" + processed +
                ", processedTime=" + processedTime +
                ", workBook=" + workBook +
                '}';
    }
}