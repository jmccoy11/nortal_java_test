package com.nortal.expenditure;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;

import com.nortal.expenditure.model.Expenditure;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class ExpenditureStatisticsCalculator {

    public ExpenditureStatistics calcExpenditureStatistics(InputStream expenditureStream) {
        List<Expenditure> exp = new ExpenditureDataExtractor().readFromFile(expenditureStream);

        // TODO needs more implementation
        HashMap<String, Double> suppliers = new HashMap<>();

        String maxFeesGainedSupplier = null;

        BigDecimal avgFee = null;
        Double totalMoneySpent = new Double(0);
        Double totalOnFeesSpent = new Double(0);

        MutablePair<String, String> smallestDifferenceProductsNames = new MutablePair<>(null, null);
        MutablePair<Double, Double> smallestDifferenceProductsValues = new MutablePair<>(0.0, 0.0);

        if(exp != null) {
            //Pair<String, String> smallestFeeDifferenceProducts = null;
            for (Expenditure expenditure : exp) {
//                System.out.println(expenditure);

                // add to suppliers hashmap
                String supplier = expenditure.getSupplier();
                Double totalCost = expenditure.getUnits() * expenditure.getUnitPrice()
                        * (1 + expenditure.getTax());
                Double feesEarned = expenditure.getUnits() * expenditure.getUnitPrice()
                        * expenditure.getTax();

                if(suppliers.get(supplier) != null) {
                    suppliers.put(supplier, (suppliers.get(supplier) + feesEarned));
                } else {
                    suppliers.put(supplier, feesEarned);
                }

                totalMoneySpent += totalCost;
                totalOnFeesSpent += feesEarned;

                if(smallestDifferenceProductsNames.getLeft() == null) {
                    smallestDifferenceProductsNames.setLeft(expenditure.getProduct());
                    smallestDifferenceProductsValues.setLeft(feesEarned);
                } else if (smallestDifferenceProductsNames.getRight() == null) {
                    smallestDifferenceProductsNames.setRight(expenditure.getProduct());
                    smallestDifferenceProductsValues.setRight(feesEarned);
                } else {
                    Double differenceWithLeft = Math.abs(smallestDifferenceProductsValues.getLeft() - feesEarned);
                    Double differenceWithRight = Math.abs(smallestDifferenceProductsValues.getRight() - feesEarned);
//
//                    System.out.println("differenceWithLeft: " + Math.abs(smallestDifferenceProductsValues.getLeft() - feesEarned));
//                    System.out.println("differenceWithRight: " + Math.abs(smallestDifferenceProductsValues.getRight() - feesEarned));

                    if(differenceWithLeft < differenceWithRight) {
                        smallestDifferenceProductsNames.setRight(expenditure.getProduct());
                        smallestDifferenceProductsValues.setRight(feesEarned);
                    } else if (differenceWithRight < differenceWithLeft) {
                        smallestDifferenceProductsNames.setLeft(expenditure.getProduct());
                        smallestDifferenceProductsValues.setLeft(feesEarned);
                    }
                }
            }

            maxFeesGainedSupplier = null;
            avgFee = new BigDecimal(totalOnFeesSpent / totalMoneySpent);

            for (String supplier : suppliers.keySet()) {
                if (maxFeesGainedSupplier == null) {
                    maxFeesGainedSupplier = supplier;
                } else {
                    if(suppliers.get(supplier) > suppliers.get(maxFeesGainedSupplier)) {
                        maxFeesGainedSupplier = supplier;
                    }
                }
            }
//
//            System.out.println();
//            System.out.println("Supplier who has gained the most money from additional Fees " + maxFeesGainedSupplier);
//            System.out.println("Average fee percentage: " + avgFee);
//            System.out.println("Smallest difference in fees between two products is: " +
//                    Math.abs(smallestDifferenceProductsValues.getLeft()-smallestDifferenceProductsValues.getRight()));
//            System.out.println("Those products are " + smallestDifferenceProductsNames.getLeft() + " and "
//                    + smallestDifferenceProductsNames.getRight());
        }

        return new ExpenditureStatistics(maxFeesGainedSupplier,
                avgFee.setScale(4, BigDecimal.ROUND_HALF_UP),
                smallestDifferenceProductsNames);
    }

    public static class ExpenditureStatistics {
        private String maxFeesGainedSupplier;
        private BigDecimal avgFee;
        private Pair<String, String> smallestFeeDifferenceProducts;

        public ExpenditureStatistics(String maxFeesGainedSupplier, BigDecimal avgFee,
                                     Pair<String, String> smallestFeeDifferenceProducts) {
            this.maxFeesGainedSupplier = maxFeesGainedSupplier;
            this.avgFee = avgFee;
            this.smallestFeeDifferenceProducts = smallestFeeDifferenceProducts;
        }

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
