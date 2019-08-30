package com.kafka.boot.prj.excel.service.process;

import com.kafka.boot.prj.excel.model.process.InputExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelProcessor implements Processable {
    private final String TC = "TC";
    private final String SG = "SG";
    private final String OGG = "Ogg. ";
    private final String FORMAT_PATTERN_NUM = "000";
    private final String FORMAT_PATTERN_FINE = "00";
    private final int FINE_COLUMN = 2;
    private final int OGG_COLUMN = 4;
    private final int ROW = 1;
    private final int SHEET = 2;

    private final InputExcel inputExcel;

    public ExcelProcessor(InputExcel inputExcel) {
        this.inputExcel = inputExcel;
        this.inputExcel.setFineNumber(addFineNumber());
        this.inputExcel.setSheetNumber(this.SHEET);
        this.inputExcel.setRowNumber(this.ROW);
        this.inputExcel.setColumnFineNumber(this.FINE_COLUMN);
        this.inputExcel.setColumnOggNumber(this.OGG_COLUMN);
        this.inputExcel.setSheet(workbook.getSheetAt(this.inputExcel.getSheetNumber()));
        Sheet sheet = this.inputExcel.getSheet();
        this.inputExcel.setSheet(sheet);
        this.inputExcel.setFineNumber(this.addFineNumber());

    }

    private String addFineNumber() {
        DataFormatter formatter = new DataFormatter();
        return findFineNumber(formatter, inputExcel.getSheet(), inputExcel.getColumnFineNumber(), inputExcel.getRowNumber());
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


    @Override
    public void renumbering() {
        final Sheet sheet = inputExcel.getSheet();
        final int columnFine = inputExcel.getColumnFineNumber();
        final int columnOgg = inputExcel.getColumnOggNumber();
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
                    cellFineValue = prefixOfFineWord + inputExcel.getFineNumber() + num;
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
                    cellOggValue = prefixOfOggWord + inputExcel.getFineNumber() + num;
                    oggCell.setCellValue(cellOggValue);
                }
            }
        }
        try (OutputStream fileOut = new FileOutputStream(inputExcel.getPath())) {
            HSSFWorkbook wb = inputExcel.getWorkBook();
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
