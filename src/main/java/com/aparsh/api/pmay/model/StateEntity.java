package com.aparsh.api.pmay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PMAY_STATE")
public class StateEntity {

    @Id
    @Column(name = "STATE_CODE")
    private String stateCode;

    @Column(name = "STATE_NAME")
    private String stateName;

    @Column(name = "SHORT_NAME")
    private String shortName;

    @Column(name = "LGD_STATE_CODE")
    private String lgdStateCode;

    @Column(name = "ST_LOCAL_NAME")
    private String stLocalName;

    public String getStateCode() { return stateCode; }
    public void setStateCode(String stateCode) { this.stateCode = stateCode; }
    public String getStateName() { return stateName; }
    public void setStateName(String stateName) { this.stateName = stateName; }
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    public String getLgdStateCode() { return lgdStateCode; }
    public void setLgdStateCode(String lgdStateCode) { this.lgdStateCode = lgdStateCode; }
    public String getStLocalName() { return stLocalName; }
    public void setStLocalName(String stLocalName) { this.stLocalName = stLocalName; }
}
