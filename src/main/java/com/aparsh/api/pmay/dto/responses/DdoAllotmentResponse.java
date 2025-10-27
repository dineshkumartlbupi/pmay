package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class DdoAllotmentResponse {
    private boolean status;
    private List<DdoAllotment> result;
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public List<DdoAllotment> getResult() { return result; }
    public void setResult(List<DdoAllotment> result) { this.result = result; }

    public static class DdoAllotment {
        private String ddoCode;
        private String allotment;
        public DdoAllotment(){}
        public DdoAllotment(String ddoCode, String allotment){ this.ddoCode = ddoCode; this.allotment = allotment; }
        public String getDdoCode() { return ddoCode; }
        public void setDdoCode(String ddoCode) { this.ddoCode = ddoCode; }
        public String getAllotment() { return allotment; }
        public void setAllotment(String allotment) { this.allotment = allotment; }
    }
}
