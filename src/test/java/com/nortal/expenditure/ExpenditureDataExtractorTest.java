package com.nortal.expenditure;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

import com.nortal.expenditure.ExpenditureDataExtractor;
import com.nortal.expenditure.model.Expenditure;

public class ExpenditureDataExtractorTest {

    List<Expenditure> expenditures = createExpenditures();

    private List<Expenditure> createExpenditures() {
        ExpenditureDataExtractor extractor = new ExpenditureDataExtractor();
        InputStream resource = ExpenditureDataExtractorTest.class.getResourceAsStream(
                "/elbonia-defence-expenditures.xlsx");
        return extractor.readFromFile(resource);
    }

    @Test
    public void testDataExtractionWithSimpleData() throws Exception {
        Expenditure expectedExpenditure = new Expenditure();
        expectedExpenditure.setDate(DateUtils.parseDate("14.02.2016","dd.MM.yyyy"));
        expectedExpenditure.setSupplier("Conemedia");
        expectedExpenditure.setType("Utilities");
        expectedExpenditure.setProduct("Gloves");
        expectedExpenditure.setUnits(1496);
        expectedExpenditure.setUnitPrice(10.0);
        expectedExpenditure.setTax(0.15);

        ExpenditureDataExtractor extractor = new ExpenditureDataExtractor();
        InputStream resource = ExpenditureDataExtractorTest.class.getResourceAsStream(
                "/simple-expenditures.xlsx");
        List<Expenditure> simpleExpenditures = extractor.readFromFile(resource);

        //assertThat(simpleExpenditures, contains(expectedExpenditure));

        Expenditure simpleExpenditure = simpleExpenditures.get(0);

        Assert.assertEquals(simpleExpenditure.getDate(), expectedExpenditure.getDate());
        Assert.assertEquals(simpleExpenditure.getSupplier(), expectedExpenditure.getSupplier());
        Assert.assertEquals(simpleExpenditure.getType(), expectedExpenditure.getType());
        Assert.assertEquals(simpleExpenditure.getProduct(), expectedExpenditure.getProduct());
        Assert.assertEquals(simpleExpenditure.getUnits(), expectedExpenditure.getUnits());
        Assert.assertEquals(simpleExpenditure.getUnitPrice(), expectedExpenditure.getUnitPrice());
        Assert.assertEquals(simpleExpenditure.getTax(), expectedExpenditure.getTax());
    }

    @Test
    public void ensureAllDataWasLoaded() {
        int expectedNumberOfEntries = 5525;
        Assert.assertEquals(expenditures.size(), expectedNumberOfEntries);
    }

    @Test
    public void testDataExtractionWithLargeFile() throws Exception{
        int indexToTest = 2993; // index correlates to line in excell - 2, (index 0 and column Title lines)

        Expenditure expectedExpenditure = new Expenditure();
        expectedExpenditure.setDate(DateUtils.parseDate("19.10.2013","dd.MM.yyyy"));
        expectedExpenditure.setSupplier("Velit Limited");
        expectedExpenditure.setType("Land vechicles");
        expectedExpenditure.setProduct("Bicycle BI392");
        expectedExpenditure.setUnits(7255);
        expectedExpenditure.setUnitPrice(130.0);
        expectedExpenditure.setTax(0.10);

        Expenditure testExpenditure = expenditures.get(indexToTest);

        Assert.assertEquals(testExpenditure.getDate(), expectedExpenditure.getDate());
        Assert.assertEquals(testExpenditure.getSupplier(), expectedExpenditure.getSupplier());
        Assert.assertEquals(testExpenditure.getType(), expectedExpenditure.getType());
        Assert.assertEquals(testExpenditure.getProduct(), expectedExpenditure.getProduct());
        Assert.assertEquals(testExpenditure.getUnits(), expectedExpenditure.getUnits());
        Assert.assertEquals(testExpenditure.getUnitPrice(), expectedExpenditure.getUnitPrice());
        Assert.assertEquals(testExpenditure.getTax(), expectedExpenditure.getTax());
    }

    @Test
    public void ensureColumnHeadersNotIncluded() {
        int indexToTest = 0;

        Expenditure expectedExpenditure = new Expenditure();
        expectedExpenditure.setSupplier("Brightbean");
        expectedExpenditure.setType("Aircraft");
        expectedExpenditure.setProduct("Paper airplane model X10");

        Expenditure testExpenditure = expenditures.get(indexToTest);

        Assert.assertEquals(testExpenditure.getSupplier(), expectedExpenditure.getSupplier());
        Assert.assertEquals(testExpenditure.getType(), expectedExpenditure.getType());
        Assert.assertEquals(testExpenditure.getProduct(), expectedExpenditure.getProduct());
    }
}
