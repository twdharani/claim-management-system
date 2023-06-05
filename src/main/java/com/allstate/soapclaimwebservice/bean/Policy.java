package com.allstate.soapclaimwebservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Policy {
    private String holderName;
    private int policyNumber;
    private String coverageName;
    private double coverageLimit;
    private double deductible;
}
