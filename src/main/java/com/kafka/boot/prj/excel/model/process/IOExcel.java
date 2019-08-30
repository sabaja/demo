package com.kafka.boot.prj.excel.model.process;

public class IOExcel {

    private final String path;
    private boolean processed;


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

}
