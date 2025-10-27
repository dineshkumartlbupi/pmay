package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class DeductionResponse {
    private boolean status;
    private List<DeductionResult> result;
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public List<DeductionResult> getResult() { return result; }
    public void setResult(List<DeductionResult> result) { this.result = result; }

    public static class DeductionResult {
        private String deductionCode;
        private String deductionName;
        public DeductionResult(){}
        public DeductionResult(String deductionCode, String deductionName){
            this.deductionCode=deductionCode; this.deductionName=deductionName;
        }
        public String getDeductionCode() { return deductionCode; }
        public void setDeductionCode(String deductionCode) { this.deductionCode = deductionCode; }
        public String getDeductionName() { return deductionName; }
        public void setDeductionName(String deductionName) { this.deductionName = deductionName; }
    }
}
