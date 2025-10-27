package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class StateResponse {
    private boolean status;
    private java.util.List<StateResult> result;

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public java.util.List<StateResult> getResult() { return result; }
    public void setResult(java.util.List<StateResult> result) { this.result = result; }

    public static class StateResult {
        private String State_Code;
        private String State_Name;
        private String Short_Name;
        private String LGD_State_Code;
        private String st_local_name;
        public String getState_Code() { return State_Code; }
        public void setState_Code(String state_Code) { State_Code = state_Code; }
        public String getState_Name() { return State_Name; }
        public void setState_Name(String state_Name) { State_Name = state_Name; }
        public String getShort_Name() { return Short_Name; }
        public void setShort_Name(String short_Name) { Short_Name = short_Name; }
        public String getLGD_State_Code() { return LGD_State_Code; }
        public void setLGD_State_Code(String LGD_State_Code) { this.LGD_State_Code = LGD_State_Code; }
        public String getSt_local_name() { return st_local_name; }
        public void setSt_local_name(String st_local_name) { this.st_local_name = st_local_name; }
    }
}
