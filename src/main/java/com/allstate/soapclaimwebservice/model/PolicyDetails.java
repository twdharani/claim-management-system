//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.06.08 at 12:56:45 PM IST 
//


package com.allstate.soapclaimwebservice.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PolicyDetails complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PolicyDetails"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HolderName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="PolicyNumber" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="CoverageName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CoverageLimit" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="deductible" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyDetails", propOrder = {
        "holderName",
        "policyNumber",
        "coverageName",
        "coverageLimit",
        "deductible"
})
public class PolicyDetails {

    @XmlElement(name = "HolderName", required = true)
    protected String holderName;
    @XmlElement(name = "PolicyNumber")
    protected int policyNumber;
    @XmlElement(name = "CoverageName", required = true)
    protected String coverageName;
    @XmlElement(name = "CoverageLimit")
    protected double coverageLimit;
    protected double deductible;

    /**
     * Gets the value of the holderName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Sets the value of the holderName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setHolderName(String value) {
        this.holderName = value;
    }

    /**
     * Gets the value of the policyNumber property.
     */
    public int getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Sets the value of the policyNumber property.
     */
    public void setPolicyNumber(int value) {
        this.policyNumber = value;
    }

    /**
     * Gets the value of the coverageName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCoverageName() {
        return coverageName;
    }

    /**
     * Sets the value of the coverageName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCoverageName(String value) {
        this.coverageName = value;
    }

    /**
     * Gets the value of the coverageLimit property.
     */
    public double getCoverageLimit() {
        return coverageLimit;
    }

    /**
     * Sets the value of the coverageLimit property.
     */
    public void setCoverageLimit(double value) {
        this.coverageLimit = value;
    }

    /**
     * Gets the value of the deductible property.
     */
    public double getDeductible() {
        return deductible;
    }

    /**
     * Sets the value of the deductible property.
     */
    public void setDeductible(double value) {
        this.deductible = value;
    }

}
