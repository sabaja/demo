package com.kafka.boot.prj.excel.service.process.io;

import com.kafka.boot.prj.excel.model.process.IOExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

public interface IOExcelService {

    public abstract void setIOExcel(IOExcel ioExcel) throws IOException;
    public abstract void setIOExcel(IOExcel ioExcel, final String destinationPath) throws IOException;
    public abstract HSSFWorkbook copyFile(final String sourcePath, final String destinationPath) throws IOException;
    public abstract HSSFWorkbook write(IOExcel ioExcel, String destinationPath) throws IOException;
}
