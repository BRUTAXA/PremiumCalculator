import domain.Policy;
import domain.PolicyObject;
import domain.RiskType;
import domain.SubInsuranceObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;

public class PremiumCalculatorImpl implements PremiumCalculator{

    @Override
    public BigDecimal calculate(Policy policy) {
        BigDecimal premiumForAllRisks = BigDecimal.ZERO;
        for (RiskType riskType : RiskType.values()){
            BigDecimal premiumForRisk = calculatePremiumForSpecificRisk(policy, riskType);
            premiumForAllRisks = premiumForAllRisks.add(premiumForRisk);
        }
        return premiumForAllRisks.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePremiumForSpecificRisk(Policy policy, RiskType riskType){
        BigDecimal sumOfSubObjectsForSpecificRisk =
                getSumOfSubObjectsForSpecificRisk(policy.getInsuranceObjects(), riskType);
        BigDecimal coefficientForSpecificRisk =
                getCoefficientForSpecificRisk(sumOfSubObjectsForSpecificRisk,riskType);
        return sumOfSubObjectsForSpecificRisk.multiply(coefficientForSpecificRisk);
    }

    private BigDecimal getCoefficientForSpecificRisk(BigDecimal sumOfSubObjectsForSpecificRisk, RiskType riskType){
        SelectionOfRiskCoefficient coefficientSelection = new SelectionOfRiskCoefficient();
        return coefficientSelection.getCoefficientForSpecificRiskType(sumOfSubObjectsForSpecificRisk, riskType);
    }

    private BigDecimal getSumOfSubObjectsForSpecificRisk(List<PolicyObject> policyObjectList, RiskType riskType){
        return policyObjectList.stream()
                .map(PolicyObject::getSubInsuranceObjects)
                .flatMap(Collection::stream)
                .filter(subInsuranceObject -> subInsuranceObject.isSpecificRisk(riskType))
                .map(SubInsuranceObject::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
