package com.nortal.expenditure;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nortal.expenditure.model.Expenditure;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpenditureDataExtractor {
    private static final Logger LOG = LoggerFactory.getLogger(ExpenditureDataExtractor.class);

    public List<Expenditure> readFromFile(InputStream inputStream) {
        List<Expenditure> expendituresList = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);
            for(Row row : sheet) {
                if(row.getRowNum() != 0) { // Don't grab the first row since it's the column headings
                    Date date = null;
                    String supplier = null;
                    String type = null;
                    String product = null;
                    Integer units = null;
                    Double unitPrice = null;
                    Double tax = null;
                    for (Cell cell : row) {
                        CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                        String text = formatter.formatCellValue(cell);
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) {
                            case 0:
                                date = DateUtils.parseDate(text,"dd.MM.yyyy");
                                break;
                            case 1:
                                supplier = text;
                                break;
                            case 2:
                                type = text;
                                break;
                            case 3:
                                product = text;
                                break;
                            case 4:
                                units = Integer.parseInt(text);
                                break;
                            case 5:
                                unitPrice = Double.parseDouble(text);
                                break;
                            case 6:
                                tax = Double.parseDouble(text);
                                tax = tax/100;
                                break;
                        }
                    }
                    Expenditure expenditure = new Expenditure(date, supplier, type, product, units, unitPrice, tax);
                    expendituresList.add(expenditure);
                }
            }
        } catch (Exception e) {
            LOG.error("Error processing stream", e);
        }

        return expendituresList;
    }

}
