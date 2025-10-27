package com.aparsh.api.pmay.dto.responses;

import java.util.List;

public class ComponentResponse {
    private boolean status;
    private List<Component> components;
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }
    public List<Component> getComponents() { return components; }
    public void setComponents(List<Component> components) { this.components = components; }

    public static class Component {
        private String code;
        private String name;
        public Component() {}
        public Component(String code, String name) { this.code = code; this.name = name; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
