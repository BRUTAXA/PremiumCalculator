package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Policy {

    private String policyNumber;
    private String policyStatus;
    private List<PolicyObject> insuranceObjects;

    public Policy(String policyNumber, String policyStatus, PolicyObject... insuranceObjects) {
        this.policyNumber = policyNumber;
        this.policyStatus = policyStatus.toUpperCase();
        this.insuranceObjects = new ArrayList<>(Arrays.asList(insuranceObjects));
    }

    public List<PolicyObject> getInsuranceObjects() {
        return insuranceObjects;
    }
}
