package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class AgencyMappingResponse {
    private boolean status;
    private List<AgencyMappingResult> result;
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public List<AgencyMappingResult> getResult() { return result; }
    public void setResult(List<AgencyMappingResult> result) { this.result = result; }

    public static class AgencyMappingResult {
        private String agencyCode;
        private String slsCode;
        public AgencyMappingResult() {}
        public AgencyMappingResult(String agencyCode, String slsCode) {
            this.agencyCode = agencyCode; this.slsCode = slsCode;
        }
        public String getAgencyCode() { return agencyCode; }
        public void setAgencyCode(String agencyCode) { this.agencyCode = agencyCode; }
        public String getSlsCode() { return slsCode; }
        public void setSlsCode(String slsCode) { this.slsCode = slsCode; }
    }
}
