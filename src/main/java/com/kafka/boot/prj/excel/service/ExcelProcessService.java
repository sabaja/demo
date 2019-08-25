package com.kafka.boot.prj.excel.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("excelManager")
public class ExcelProcessService {

    private final String TC = "TC";
    private final String SG = "SG";
    private final String PATTERN_FINE_NUMBER = "00";
    private final String PATTERN_OGG_NUMBER = "000";
    private final int COLUMN = 2;
    private final int ROW = 1;
    private final int SHEET = 1;
    private String fineNumber;

    public static void main(String[] args) {

        try {
            ExcelProcessService service = new ExcelProcessService();
            DataFormatter formatter = new DataFormatter();
            //FileInputStream file = new FileInputStream(new File("C:\\Users\\sabatinija\\Desktop\\VODAFONE\\SG7581 - GigaRicarica\\TestBook\\TestBook_Progettazione Giga Ricarica - Copy.xls"));
            FileInputStream file = new FileInputStream(new File("TestBook_Progettazione Giga Ricarica - Copy.xls"));
            Workbook workbook = new HSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(service.SHEET);
            service.setFineNumber(formatter, sheet, service.COLUMN, service.ROW, service.PATTERN_FINE_NUMBER);
            System.out.println("fineNumber: " + service.fineNumber);
            service.renumberingOfTestName(sheet, service.COLUMN, service.TC.concat(service.fineNumber));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFineNumber(final DataFormatter formatter, final Sheet sheet, final int COLUMN, final int ROW, final String pattern) {
        String tempNumber = findFineNumber(formatter, sheet, COLUMN, ROW);
        fineNumber = formatNumber(Integer.valueOf(tempNumber), pattern);
    }

    private String findFineNumber(DataFormatter formatter, Sheet sheet, final int COLUMN, final int ROW) {
        Row row = sheet.getRow(ROW);
        Cell cell = row.getCell(COLUMN);
        String text = formatter.formatCellValue(cell);
        return findFineNumber(text);
    }

    private String findFineNumber(String text) {
        Integer fineNumber = 0;
        Pattern word = Pattern.compile(TC);
        Matcher match = word.matcher(text);
        while (match.find()) {
            System.out.println("Found TC at index " + match.start() + " - " + (match.end()) + " - " + text.length());
            fineNumber = Integer.valueOf(text.substring(match.end(), match.end() + 2));
        }
        return String.valueOf(fineNumber);
    }

    private String formatNumber(final Integer tempNum, final String pattern) {
        String fineNumber;
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        fineNumber = myFormatter.format(tempNum);
        return fineNumber;
    }

    private void renumberingOfTestName(final Sheet sheet, final int COLUMN, final String TC_NUM) {
        final String INTIAL_NUMB = "001";
        DataFormatter formatter = new DataFormatter();
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (Objects.nonNull(row)) {
                    String cellValue;
                    if ((cellValue = formatter.formatCellValue(row.getCell(COLUMN))).contains(SG)) {
                        cellValue = cellValue.trim();
                        final int start = cellValue.indexOf(TC_NUM) + TC_NUM.length();
                        final int end = cellValue.length();
                        String lll = cellValue.substring(start, end);
                        System.out.println(lll);
                    }
                    /*
                    if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        valuesadd(cell.getNumericCellValue());
                    } else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA && cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
                        valuesadd(c.getNumericCellValue());
                    }*/


                }
            }
        }
    }


