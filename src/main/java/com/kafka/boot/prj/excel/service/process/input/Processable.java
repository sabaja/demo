package com.kafka.boot.prj.excel.service.process.input;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface Processable {
    public abstract HSSFWorkbook renumbering();
}
