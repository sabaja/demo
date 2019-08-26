package com.kafka.boot.prj;

import com.kafka.boot.prj.excel.dto.ExcelToProcess;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class PrjApplication {

    public void main(String[] args) {
        SpringApplication.run(PrjApplication.class, args);
    }


    @Bean
    public HSSFWorkbook excelProcessService() throws IOException {
        FileInputStream file = new FileInputStream(new File("TestBook_Progettazione Giga Ricarica - Copy.xls"));
        return new HSSFWorkbook(file);
    }

    @Bean
    public ExcelToProcess excelToProcess() throws IOException {
        return new ExcelToProcess(excelProcessService());
    }

}

