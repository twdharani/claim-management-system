package com.allstate.soapclaimwebservice.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy implements Serializable {
    private String holderName;
    private int policyNumber;
    private String coverageName;
    private double coverageLimit;
    private double deductible;
}
