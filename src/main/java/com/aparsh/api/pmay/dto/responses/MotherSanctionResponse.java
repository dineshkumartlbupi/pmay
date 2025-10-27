package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class MotherSanctionResponse {
    private boolean status;
    private List<MotherSanction> result;
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public List<MotherSanction> getResult() { return result; }
    public void setResult(List<MotherSanction> result) { this.result = result; }

    public static class MotherSanction {
        private String id;
        private String limit;
        public MotherSanction() {}
        public MotherSanction(String id, String limit) { this.id = id; this.limit = limit; }
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getLimit() { return limit; }
        public void setLimit(String limit) { this.limit = limit; }
    }
}
