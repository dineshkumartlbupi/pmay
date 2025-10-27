package com.aparsh.api.pmay.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DscEnrollmentAck")
public class DscEnrollmentAck {
    private String status;
    private String message;

    @XmlElement(name = "Status")
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @XmlElement(name = "Message")
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
