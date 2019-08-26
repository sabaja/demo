package com.kafka.boot.prj.excel.service;

import com.kafka.boot.prj.excel.dto.ExcelToProcess;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("excelManager")
public class ExcelProcessService {

    private final String TC = "TC";
    private final String SG = "SG";
    private final String OGG = "Ogg. ";
    private final String FORMAT_PATTERN_NUM = "000";
    private final String FORMAT_PATTERN_FINE = "00";
    private final int FINE_COLUMN = 2;
    private final int OGG_COLUMN = 4;
    private final int ROW = 1;
    private final int SHEET = 2;

    @Autowired
    private ExcelToProcess excelToProcess;


    public ExcelProcessService() {
    }


    private String addFineNumber(final ExcelToProcess excelToProcess) {
        DataFormatter formatter = new DataFormatter();
        return findFineNumber(formatter, excelToProcess.getSheet(), excelToProcess.getColumnFineNumber(), excelToProcess.getRowNumber());
        //return formatNumber(Integer.valueOf(tempNumber), pattern);
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
        return formatNumber(fineNumber, this.FORMAT_PATTERN_FINE);
    }

    private String formatNumber(final Integer tempNum, final String pattern) {
        String fineNumber;
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        fineNumber = myFormatter.format(tempNum);
        return fineNumber;
    }

    public void renumbering(ExcelToProcess excel) {
        final Sheet sheet = excel.getSheet();
        final int columnFine = excel.getColumnFineNumber();
        final int columnOgg = excel.getColumnOggNumber();
        DataFormatter formatter = new DataFormatter();
        String prefixOfFineWord = null, prefixOfOggWord = null;
        String cellFineValue, cellOggValue;
        for (int i = 0, fineIndex = 0, oggIndex = 0; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (Objects.nonNull(row)) {
                Cell fineCell = row.getCell(columnFine);
                Cell oggCell = row.getCell(columnOgg);

                //Renumbering of TC
                if ((cellFineValue = formatter.formatCellValue(fineCell)).contains(SG)) {
                    fineIndex++;
                    //cellValue = cellValue.trim();
                    final int startFineWord = cellFineValue.indexOf(TC) + TC.length();
                    // System.out.printf("%d ", startFineWord);

                    if (Objects.isNull(prefixOfFineWord)) {
                        prefixOfFineWord = cellFineValue.substring(0, startFineWord);
                    }
                    String num = this.formatNumber(fineIndex, FORMAT_PATTERN_NUM);
                    System.out.println(prefixOfFineWord + " " + num);
                    cellFineValue = prefixOfFineWord + excel.getFineNumber() + num;
                    fineCell.setCellValue(cellFineValue);
                }

                //Renumbering of Ogg.
                if ((cellOggValue = formatter.formatCellValue(oggCell)).contains(OGG)) {
                    oggIndex++;
                    //cellValue = cellValue.trim();
                    final int startFineWord = cellOggValue.indexOf(OGG) + OGG.length();
                    // System.out.printf("%d ", startFineWord);

                    if (Objects.isNull(prefixOfOggWord)) {
                        prefixOfOggWord = cellOggValue.substring(0, startFineWord);
                    }
                    String num = this.formatNumber(oggIndex, FORMAT_PATTERN_NUM);
                    System.out.println(prefixOfOggWord + " " + num);
                    cellOggValue = prefixOfOggWord + excel.getFineNumber() + num;
                    oggCell.setCellValue(cellOggValue);
                }
            }
        }
        try (OutputStream fileOut = new FileOutputStream(excel.getPath())) {
            HSSFWorkbook wb = excel.getWorkBook();
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        try {
            String path = "C:\\Users\\sabatinija\\Desktop\\Testrename\\TestBook_Progettazione Giga Ricarica - Copy.xls";
            //String path = "TestBook_Progettazione Giga Ricarica - Copy.xls";
            ExcelProcessService service = new ExcelProcessService();
            FileInputStream file = new FileInputStream(new File(path));
            // FileInputStream file = new FileInputStream(new File());
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            ExcelToProcess excel = new ExcelToProcess(workbook);
            excel.setPath(path);
            excel.setSheetNumber(service.SHEET);
            excel.setRowNumber(service.ROW);
            excel.setColumnFineNumber(service.FINE_COLUMN);
            excel.setColumnOggNumber(service.OGG_COLUMN);
            excel.setSheet(workbook.getSheetAt(excel.getSheetNumber()));
            Sheet sheet = excel.getSheet();
            excel.setSheet(sheet);
            excel.setFineNumber(service.addFineNumber(excel));
            service.renumbering(excel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


