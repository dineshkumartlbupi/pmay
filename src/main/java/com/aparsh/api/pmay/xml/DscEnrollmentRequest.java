package com.aparsh.api.pmay.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DscEnrollmentRequest")
public class DscEnrollmentRequest {
    private String applicantName;
    private String aadhaar;
    @XmlElement(name = "ApplicantName")
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    @XmlElement(name = "Aadhaar")
    public String getAadhaar() { return aadhaar; }
    public void setAadhaar(String aadhaar) { this.aadhaar = aadhaar; }
}
