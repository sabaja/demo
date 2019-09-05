package com.kafka.boot.prj.excel.service.process.io;

import com.kafka.boot.prj.excel.model.process.IOExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;

public interface IOExcelService {

    public abstract IOExcel setIOExcel(IOExcel ioExcel) throws IOException;
    public abstract HSSFWorkbook copyWorkbook(final String sourcePath, final String destinationPath) throws IOException;
    public abstract HSSFWorkbook writeWorkbook(IOExcel ioExcel) throws IOException;
}
