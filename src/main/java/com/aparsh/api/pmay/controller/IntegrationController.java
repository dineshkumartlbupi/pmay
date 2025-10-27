package com.aparsh.api.pmay.controller;

import com.aparsh.api.pmay.dto.requests.*;
import com.aparsh.api.pmay.dto.responses.*;
import com.aparsh.api.pmay.service.IntegrationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class IntegrationController {

    private final IntegrationService service;

    public IntegrationController(IntegrationService service) {
        this.service = service;
    }

    @PostMapping(value = "/getState", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StateResponse> getState(@RequestBody StateRequest req) {
        return ResponseEntity.ok(service.getState(req));
    }

    @PostMapping(value = "/getSNADetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SnaDetailsResponse> getSNADetails(@RequestBody SnaDetailsRequest req) {
        return ResponseEntity.ok(service.getSnaDetails(req));
    }

    @PostMapping(value = "/AgencyMappingDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgencyMappingResponse> agencyMappingDetails(@RequestBody AgencyMappingRequest req){
        return ResponseEntity.ok(service.getAgencyMapping(req));
    }

    @PostMapping(value = "/getDeduction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeductionResponse> getDeduction(@RequestBody DeductionRequest req){
        return ResponseEntity.ok(service.getDeduction(req));
    }

    @PostMapping(value = "/getComponents", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentResponse> getComponents(@RequestBody ComponentRequest req){
        return ResponseEntity.ok(service.getComponents(req));
    }

    @PostMapping(value = "/ddoAllotmentDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DdoAllotmentResponse> ddoAllotmentDetails(@RequestBody DdoAllotmentRequest req){
        return ResponseEntity.ok(service.getDdoAllotmentDetails(req));
    }

    @PostMapping(value = "/motherSanctionLimit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MotherSanctionResponse> motherSanctionLimit(@RequestBody MotherSanctionRequest req){
        return ResponseEntity.ok(service.getMotherSanctionLimit(req));
    }

    @PostMapping(value = "/getErrorMaster", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorMasterResponse> getErrorMaster(@RequestBody ErrorMasterRequest req){
        return ResponseEntity.ok(service.getErrorMaster(req));
    }

    // XML endpoints
    @PostMapping(value = "/dscEnrollment", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> dscEnrollment(@RequestBody String xml){
        String ack = service.processDscEnrollmentXml(xml);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(ack);
    }

    @PostMapping(value = "/ftoSubmit", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> ftoSubmit(@RequestBody String xml){
        String ack = service.processFtoXml(xml);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(ack);
    }
}
