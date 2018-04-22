package com.nortal.expenditure;

import java.io.InputStream;

import com.nortal.expenditure.ExpenditureStatisticsCalculator.ExpenditureStatistics;

public class ExpenditureStatisticsMain {

    public static void main(String[] args) {
        InputStream resource = ExpenditureStatisticsMain.class.getResourceAsStream(
                "/elbonia-defence-expenditures.xlsx");
        ExpenditureStatisticsCalculator calc = new ExpenditureStatisticsCalculator();
        ExpenditureStatistics st = calc.calcExpenditureStatistics(resource);
        
        System.out.println(" ---- Expenditure Statistics ---- ");
        System.out.println("Average fee percentage: " + st.getAvgFee());
        System.out.println("Supplier with highest tax sum: " + st.getMaxFeesGainedSupplier());
        System.out.println("Products with most similar tax sum: " + st.getSmallestFeeDifferenceProducts());
    }
}
