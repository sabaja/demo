package com.kafka.boot.prj.excel.service.process.input;

import com.kafka.boot.prj.excel.model.process.InputExcel;
import com.kafka.boot.prj.excel.service.process.constant.ExcelDataProcess;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExcelProcessor implements Processable {

    private final InputExcel inputExcel;


    public ExcelProcessor(InputExcel inputExcel) {
        this.inputExcel = inputExcel;
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
        Pattern word = Pattern.compile(ExcelDataProcess.TC.getAString());
        Matcher match = word.matcher(text);
        while (match.find()) {
            System.out.println("Found TC at index " + match.start() + " - " + (match.end()) + " - " + text.length());
            fineNumber = Integer.valueOf(text.substring(match.end(), match.end() + 2));
        }
        return formatNumber(fineNumber, ExcelDataProcess.FORMAT_PATTERN_FINE.getAString());
    }


    private String formatNumber(final Integer tempNum, final String pattern) {
        String fineNumber;
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        fineNumber = myFormatter.format(tempNum);
        return fineNumber;
    }


    @Override
    public HSSFWorkbook renumbering() {
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
                if ((cellFineValue = formatter.formatCellValue(fineCell)).contains(ExcelDataProcess.SG.getAString())) {
                    fineIndex++;
                    //cellValue = cellValue.trim();
                    final String TC = ExcelDataProcess.TC.getAString();
                    final int startFineWord = cellFineValue.indexOf(TC) + TC.length();
                    // System.out.printf("%d ", startFineWord);

                    if (Objects.isNull(prefixOfFineWord)) {
                        prefixOfFineWord = cellFineValue.substring(0, startFineWord);
                    }
                    String num = this.formatNumber(fineIndex, ExcelDataProcess.TC.FORMAT_PATTERN_NUM.getAString());
                    System.out.println(prefixOfFineWord + " " + num);
                    cellFineValue = prefixOfFineWord + inputExcel.getFineNumber() + num;
                    fineCell.setCellValue(cellFineValue);
                }

                //Renumbering of Ogg.
                if ((cellOggValue = formatter.formatCellValue(oggCell)).contains(ExcelDataProcess.OGG.getAString())) {
                    oggIndex++;
                    //cellValue = cellValue.trim();
                    final String OGG = ExcelDataProcess.OGG.getAString();
                    final int startFineWord = cellOggValue.indexOf(OGG) + OGG.length();
                    // System.out.printf("%d ", startFineWord);

                    if (Objects.isNull(prefixOfOggWord)) {
                        prefixOfOggWord = cellOggValue.substring(0, startFineWord);
                    }
                    String num = this.formatNumber(oggIndex, ExcelDataProcess.TC.FORMAT_PATTERN_NUM.getAString());
                    System.out.println(prefixOfOggWord + " " + num);
                    cellOggValue = prefixOfOggWord + inputExcel.getFineNumber() + num;
                    oggCell.setCellValue(cellOggValue);
                }
            }
        }

        return this.inputExcel.getIoExcel().getWorkBook();
    }
}
