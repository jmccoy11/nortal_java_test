package com.nortal.expenditure;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import com.nortal.expenditure.model.Expenditure;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpenditureDataExtractor {
    private static final Logger LOG = LoggerFactory.getLogger(ExpenditureDataExtractor.class);

    public List<Expenditure> readFromFile(InputStream inputStream) {
        // TODO needs more implementation. See Apache POI documentation for further details

        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
        } catch (Exception e) {
            LOG.error("Error processing stream", e);
        }

        return Collections.emptyList();
    }

}
