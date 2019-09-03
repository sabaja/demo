package com.kafka.boot.prj.excel.service.process.io;

import com.kafka.boot.prj.excel.model.process.IOExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class IOExcelServiceImpl implements IOExcelService {

    @Override
    public void setIOExcel(IOExcel ioExcel) throws IOException {
        if(ioExcel.isProcessed()){
            return;
        }
        String path = ioExcel.getPath();
        FileInputStream file = new FileInputStream(new File(path));
        HSSFWorkbook workbook = new HSSFWorkbook(file);
        ioExcel.setWorkBook(workbook);
        ioExcel.setProcessed(true);
    }

    @Override
    public void setIOExcel(IOExcel ioExcel, String destinationPath) throws IOException {
        if(ioExcel.isProcessed()){
            return;
        }
        String path = ioExcel.getPath();
        ioExcel.setWorkBook(copyFile(path,destinationPath));
        ioExcel.setProcessed(true);
    }

    @Override
    public HSSFWorkbook copyFile(final String sourcePath, final String destinationPath) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(sourcePath));
        HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
        FileOutputStream outputStream = new FileOutputStream(destinationPath);
        workbook.write(outputStream);
        workbook.close();
        return workbook;
    }

    @Override
    public HSSFWorkbook write(IOExcel ioExcel, String destinationPath) throws IOException {
        OutputStream fileOut = new FileOutputStream(destinationPath);
        HSSFWorkbook workbook = ioExcel.getWorkBook();
        workbook.write(fileOut);
        workbook.close();
        return workbook;
    }


}
