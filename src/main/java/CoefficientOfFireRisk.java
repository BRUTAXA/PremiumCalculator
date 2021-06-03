import java.math.BigDecimal;

public class CoefficientOfFireRisk implements GetCoefficientOfRiskType{

    private BigDecimal defaultCoefficient = new BigDecimal("0.014");
    private BigDecimal overDefaultCoefficient = new BigDecimal("0.024");

    @Override
    public BigDecimal getCoefficient(BigDecimal sum){

        if (sum.compareTo(new BigDecimal("100")) > 0){
            return overDefaultCoefficient;
        } else {
            return defaultCoefficient;
        }
    }
}
