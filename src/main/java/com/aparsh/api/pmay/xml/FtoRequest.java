package com.aparsh.api.pmay.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FtoRequest")
public class FtoRequest {
    private String ftoNumber;
    @XmlElement(name = "FtoNumber")
    public String getFtoNumber() { return ftoNumber; }
    public void setFtoNumber(String ftoNumber) { this.ftoNumber = ftoNumber; }
}
