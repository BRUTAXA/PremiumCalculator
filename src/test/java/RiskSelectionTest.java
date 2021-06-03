import  org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class RiskSelectionTest {

    @Test
    public void testDefaultFireCoefficient(){
        CoefficientOfFireRisk coefficientOfFireRisk = new CoefficientOfFireRisk();
        BigDecimal sum = new BigDecimal("40");
        BigDecimal expectedCoefficient = new BigDecimal("0.014");
        BigDecimal result = coefficientOfFireRisk.getCoefficient(sum);
        assertEquals(expectedCoefficient,result);
    }

    @Test
    public void testDefaultFireCoefficientForSum100(){
        CoefficientOfFireRisk coefficientOfFireRisk = new CoefficientOfFireRisk();
        BigDecimal sum = new BigDecimal("100");
        BigDecimal expectedCoefficient = new BigDecimal("0.014");
        BigDecimal result = coefficientOfFireRisk.getCoefficient(sum);
        assertEquals(expectedCoefficient,result);
    }

    @Test
    public void testFireOverDefaultCoefficient(){
        CoefficientOfFireRisk coefficientOfFireRisk = new CoefficientOfFireRisk();
        BigDecimal sum = new BigDecimal("146");
        BigDecimal expectedCoefficient = new BigDecimal("0.024");
        BigDecimal result = coefficientOfFireRisk.getCoefficient(sum);
        assertEquals(expectedCoefficient,result);
    }

    @Test
    public void testDefaultTheftCoefficient(){
        CoefficientOfTheftRisk coefficientOfTheftRisk = new CoefficientOfTheftRisk();
        BigDecimal sum = new BigDecimal("10");
        BigDecimal expectedCoefficient = new BigDecimal("0.11");
        BigDecimal result = coefficientOfTheftRisk.getCoefficient(sum);
        assertEquals(expectedCoefficient,result);
    }

    @Test
    public void testTheftRiskIfSumIs15(){
        CoefficientOfTheftRisk coefficientOfTheftRisk = new CoefficientOfTheftRisk();
        BigDecimal sum = new BigDecimal("15");
        BigDecimal expectedCoefficient = new BigDecimal("0.05");
        BigDecimal result = coefficientOfTheftRisk.getCoefficient(sum);
        assertEquals(expectedCoefficient,result);
    }

    @Test
    public void testOverDefaultTheftRisk(){
        CoefficientOfTheftRisk coefficientOfTheftRisk = new CoefficientOfTheftRisk();
        BigDecimal sum = new BigDecimal("20");
        BigDecimal expectedCoefficient = new BigDecimal("0.05");
        BigDecimal result = coefficientOfTheftRisk.getCoefficient(sum);
        assertEquals(expectedCoefficient,result);
    }
}
