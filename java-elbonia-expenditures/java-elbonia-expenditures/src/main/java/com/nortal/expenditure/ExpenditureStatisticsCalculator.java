package com.nortal.expenditure;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import com.nortal.expenditure.model.Expenditure;

import org.apache.commons.lang3.tuple.Pair;

public class ExpenditureStatisticsCalculator {

    public ExpenditureStatistics calcExpenditureStatistics(InputStream expenditureStream) {
        List<Expenditure> exp = new ExpenditureDataExtractor().readFromFile(expenditureStream);
        // TODO needs more implementation

        return new ExpenditureStatistics();
    }

    public static class ExpenditureStatistics {
        private String maxFeesGainedSupplier;
        private BigDecimal avgFee;
        private Pair<String, String> smallestFeeDifferenceProducts;

        /**
         * @return Name of the supplier who has gained most money in additional fees
         */
        public String getMaxFeesGainedSupplier() {
            return maxFeesGainedSupplier;
        }

        public void setMaxFeesGainedSupplier(String maxFeesGainedSupplier) {
            this.maxFeesGainedSupplier = maxFeesGainedSupplier;
        }

        /**
         * @return Average fee percentage in decimal form: 34.5% = 0.345
         */
        public BigDecimal getAvgFee() {
            return avgFee;
        }

        public void setAvgFee(BigDecimal avgFee) {
            this.avgFee = avgFee;
        }

        /**
         * @return Pair of product names that have smallest difference in total paid fees
         */
        public Pair<String, String> getSmallestFeeDifferenceProducts() {
            return smallestFeeDifferenceProducts;
        }

        public void setSmallestFeeDifferenceProducts(Pair<String, String> smallestFeeDifferenceProducts) {
            this.smallestFeeDifferenceProducts = smallestFeeDifferenceProducts;
        }
    }
}
