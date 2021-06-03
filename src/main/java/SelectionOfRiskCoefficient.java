import domain.RiskType;

import java.math.BigDecimal;

public class SelectionOfRiskCoefficient {

    BigDecimal getCoefficientForSpecificRiskType(BigDecimal sum, RiskType riskType){
        return switch (riskType) {
            case FIRE -> new CoefficientOfFireRisk().getCoefficient(sum);
            case THEFT -> new CoefficientOfTheftRisk().getCoefficient(sum);
        };
    }
}
