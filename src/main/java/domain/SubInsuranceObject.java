package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubInsuranceObject {

    private String name;
    private BigDecimal price;
    private List<RiskType> risks;

    public SubInsuranceObject(String name, BigDecimal price, RiskType ... risks) {
        this.name = name;
        this.price = price;
        this.risks = new ArrayList<>(Arrays.asList(risks));
    }

    public boolean isSpecificRisk(RiskType riskType){
        return risks.contains(riskType);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
