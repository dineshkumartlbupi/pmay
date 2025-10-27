package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class SnaDetailsResponse {
    private List<SnaDtl> snaDtl;
    public List<SnaDtl> getSnaDtl() { return snaDtl; }
    public void setSnaDtl(List<SnaDtl> snaDtl) { this.snaDtl = snaDtl; }

    public static class SnaDtl {
        private String SLSCODE;
        private String SNA;
        private String CSSCODE;
        private String CENTERSHARE;
        public String getSLSCODE() { return SLSCODE; }
        public void setSLSCODE(String SLSCODE) { this.SLSCODE = SLSCODE; }
        public String getSNA() { return SNA; }
        public void setSNA(String SNA) { this.SNA = SNA; }
        public String getCSSCODE() { return CSSCODE; }
        public void setCSSCODE(String CSSCODE) { this.CSSCODE = CSSCODE; }
        public String getCENTERSHARE() { return CENTERSHARE; }
        public void setCENTERSHARE(String CENTERSHARE) { this.CENTERSHARE = CENTERSHARE; }
    }
}
