package com.allstate.soapclaimwebservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
    private int claimNumber;
    private int policyNumber;
}
