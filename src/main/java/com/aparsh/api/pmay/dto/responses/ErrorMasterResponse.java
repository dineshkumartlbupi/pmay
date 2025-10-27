package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class ErrorMasterResponse {
    private boolean status;
    private List<ErrorMaster> result;
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public List<ErrorMaster> getResult() { return result; }
    public void setResult(List<ErrorMaster> result) { this.result = result; }

    public static class ErrorMaster {
        private String code;
        private String description;
        public ErrorMaster(){}
        public ErrorMaster(String code, String description){ this.code = code; this.description = description; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}
