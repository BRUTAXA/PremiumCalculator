import java.math.BigDecimal;

public class CoefficientOfTheftRisk implements GetCoefficientOfRiskType{

    private BigDecimal defaultCoefficient = new BigDecimal("0.11");
    private BigDecimal overDefaultCoefficient = new BigDecimal("0.05");

    @Override
    public BigDecimal getCoefficient(BigDecimal sum){

        if (sum.compareTo(new BigDecimal("15")) >= 0){
            return overDefaultCoefficient;
        } else {
            return defaultCoefficient;
        }
    }
}
