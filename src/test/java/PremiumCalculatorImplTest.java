import domain.Policy;
import domain.PolicyObject;
import domain.RiskType;
import domain.SubInsuranceObject;
import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;


public class PremiumCalculatorImplTest {

    PremiumCalculator calculator = new PremiumCalculatorImpl();

    @Test
    public void test1(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("100"), RiskType.FIRE);
        SubInsuranceObject subInsuranceObject2 = new SubInsuranceObject("PC", new BigDecimal("8"), RiskType.THEFT);
        PolicyObject policyObject = new PolicyObject("A House",subInsuranceObject1, subInsuranceObject2);
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED", policyObject);


        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("2.28");
        assertEquals(expectedPolicyPremium,calculatedPolicyPremium);
    }

    @Test
    public void test2(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("250"), RiskType.FIRE);
        SubInsuranceObject subInsuranceObject2 = new SubInsuranceObject("TV", new BigDecimal("250"), RiskType.FIRE);
        SubInsuranceObject subInsuranceObject3 = new SubInsuranceObject("PC", new BigDecimal("100"), RiskType.THEFT);
        SubInsuranceObject subInsuranceObject4 = new SubInsuranceObject("PC", new BigDecimal("2.51"), RiskType.THEFT);
        PolicyObject policyObject1 = new PolicyObject("A House",subInsuranceObject1 , subInsuranceObject3);
        PolicyObject policyObject2 = new PolicyObject("A Garage", subInsuranceObject4, subInsuranceObject2);
        Policy policy = new Policy("LV20-02-100000-5", "APPROVED", policyObject1, policyObject2);

        BigDecimal expectedPolicyPremium = new BigDecimal("17.13");
        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testIfNoSubInsuranceObjects(){
        PolicyObject policyObject1 = new PolicyObject("A House");
        PolicyObject policyObject2 = new PolicyObject("A Garage");
        Policy policy = new Policy("LV20-02-100000-5", "APPROVED", policyObject1, policyObject2);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("0.00");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testTwoObjectsOneSubObjectWithFireRiskType(){
        SubInsuranceObject subInsuranceObject = new SubInsuranceObject("TV", new BigDecimal("200"), RiskType.FIRE);
        PolicyObject policyObject1 = new PolicyObject("A House", subInsuranceObject);
        PolicyObject policyObject2 = new PolicyObject("A Garage");
        Policy policy = new Policy("LV20-02-100000-5", "APPROVED", policyObject1, policyObject2);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("4.80");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testTwoObjectsOneSubObjectWithTheftRiskType(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("200"), RiskType.THEFT);
        PolicyObject policyObject1 = new PolicyObject("A House");
        PolicyObject policyObject2 = new PolicyObject("A Garage", subInsuranceObject1);
        Policy policy = new Policy("LV20-02-100000-5", "APPROVED", policyObject1, policyObject2);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("10.00");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testOneObjectTwoSubObjectsBothRisks(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("100"), RiskType.FIRE, RiskType.THEFT);
        SubInsuranceObject subInsuranceObject2 = new SubInsuranceObject("PC", new BigDecimal("8"), RiskType.THEFT, RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("A House", subInsuranceObject1, subInsuranceObject2);
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED", policyObject);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("7.99");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testOneObjectOneSubObjectLowFireRisk(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("52"), RiskType.FIRE);
        SubInsuranceObject subInsuranceObject2 = new SubInsuranceObject("PC", new BigDecimal("48"), RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("A House", subInsuranceObject1, subInsuranceObject2);
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED", policyObject);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("1.40");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testOneObjectOneSubObjectHighFireRisk(){
        SubInsuranceObject subInsuranceObject = new SubInsuranceObject("TV", new BigDecimal("180"), RiskType.FIRE);
        PolicyObject policyObject = new PolicyObject("A House", subInsuranceObject);
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED", policyObject);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("4.32");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testTwoObjectOneSubObjectLowTheftRisk(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("10"), RiskType.THEFT);
        SubInsuranceObject subInsuranceObject2 = new SubInsuranceObject("PC", new BigDecimal("4"), RiskType.THEFT);
        PolicyObject policyObject1 = new PolicyObject("A House", subInsuranceObject1);
        PolicyObject policyObject2 = new PolicyObject("A Garage", subInsuranceObject2);
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED", policyObject1, policyObject2);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("1.54");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

    @Test
    public void testTwoObjectOneSubObjectHighTheftRisk(){
        SubInsuranceObject subInsuranceObject1 = new SubInsuranceObject("TV", new BigDecimal("100"), RiskType.THEFT);
        SubInsuranceObject subInsuranceObject2 = new SubInsuranceObject("PC", new BigDecimal("400"), RiskType.THEFT);
        PolicyObject policyObject1 = new PolicyObject("A House", subInsuranceObject1);
        PolicyObject policyObject2 = new PolicyObject("A Garage", subInsuranceObject2);
        Policy policy = new Policy("LV20-02-100000-5", "REGISTERED", policyObject1, policyObject2);

        BigDecimal calculatedPolicyPremium = calculator.calculate(policy);
        BigDecimal expectedPolicyPremium = new BigDecimal("25.00");
        assertEquals(expectedPolicyPremium, calculatedPolicyPremium);
    }

}