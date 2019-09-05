package com.kafka.boot.prj.excel.service.process.io;

import com.kafka.boot.prj.excel.model.process.IOExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Instant;

@Service
public class IOExcelServiceImpl implements IOExcelService {

    private Logger logger = LoggerFactory.getLogger(IOExcelServiceImpl.class);

    @Override
    public IOExcel setIOExcel(IOExcel ioExcel) throws IOException {
        final String path = ioExcel.getSourcePath();
        final String destinationPath = ioExcel.getDestinationPath();
        ioExcel.setWorkBook(copyWorkbook(path, destinationPath));
        logger.info("{} was set", ioExcel);
        return ioExcel;
    }

    @Override
    public HSSFWorkbook copyWorkbook(final String sourcePath, final String destinationPath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(sourcePath));
        HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
        FileOutputStream outputStream = new FileOutputStream(destinationPath);
        workbook.write(outputStream);
        workbook.close();
        logger.info("destination {} was copied to {}", sourcePath, destinationPath);
        return workbook;
    }

    @Override
    public HSSFWorkbook writeWorkbook(IOExcel ioExcel) throws IOException {
        final String destinationPath = ioExcel.getDestinationPath();
        OutputStream fileOut = new FileOutputStream(destinationPath);
        HSSFWorkbook workbook = ioExcel.getWorkBook();
        workbook.write(fileOut);
        workbook.close();
        ioExcel.setProcessedTime(Instant.now());
        ioExcel.setProcessed(true);
        return workbook;
    }

}
