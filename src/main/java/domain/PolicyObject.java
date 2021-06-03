package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolicyObject {

    private String objectName;
    private List<SubInsuranceObject> subInsuranceObjects;

    public PolicyObject(String objectName, SubInsuranceObject ... subInsuranceObjects) {
        this.objectName = objectName;
        this.subInsuranceObjects = new ArrayList<>(Arrays.asList(subInsuranceObjects));
    }

    public List<SubInsuranceObject> getSubInsuranceObjects() {
        return subInsuranceObjects;
    }
}
