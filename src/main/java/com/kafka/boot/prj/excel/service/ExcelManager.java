package com.kafka.boot.prj.excel.service;

import com.kafka.boot.prj.excel.service.process.input.InputExcelProcessImpl;
import com.kafka.boot.prj.excel.service.process.io.IOExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelManager {

    /*
    private final String TC = "TC";
    private final String SG = "SG";
    private final String OGG = "Ogg. ";
    private final String FORMAT_PATTERN_NUM = "000";
    private final String FORMAT_PATTERN_FINE = "00";
    private final int FINE_COLUMN = 2;
    private final int OGG_COLUMN = 4;
    private final int ROW = 1;
    private final int SHEET = 2;
    */
    private InputExcelProcessImpl processor;
    private IOExcelService excelService;

    /*
    @Autowired
    public ExcelManager(InputExcelProcessImpl processor, IOExcelService excelService) {
        this.processor = processor;
        this.excelService = excelService;
    }*/

/*
    public static void main(String[] args) {
/*
        try {

            String path = "C:\\Users\\sabatinija\\Desktop\\Testrename\\TestBook_Progettazione Giga Ricarica - Copy.xls";
            //String path = "TestBook_Progettazione Giga Ricarica - Copy.xls";
            FileInputStream file = new FileInputStream(new File(path));
            // FileInputStream file = new FileInputStream(new File());
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            InputExcel excel = new InputExcel(workbook);
            ExcelManager service = new ExcelManager(excel);

            excel.setPath(path);
            excel.setSheetNumber(service.SHEET);
            excel.setRowNumber(service.ROW);
            excel.setColumnFineNumber(service.FINE_COLUMN);
            excel.setColumnOggNumber(service.OGG_COLUMN);
            excel.setSheet(workbook.getSheetAt(excel.getSheetNumber()));
            Sheet sheet = excel.getSheet();
            excel.setSheet(sheet);
            excel.setFineNumber(service.addFineNumber());

            service.renumbering();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }*/


    public void run() {

    }
}



