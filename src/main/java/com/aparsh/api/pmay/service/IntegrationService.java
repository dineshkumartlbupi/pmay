package com.aparsh.api.pmay.service;

import com.aparsh.api.pmay.dto.requests.*;
import com.aparsh.api.pmay.dto.responses.*;

public interface IntegrationService {
    StateResponse getState(StateRequest req);
    SnaDetailsResponse getSnaDetails(SnaDetailsRequest req);
    AgencyMappingResponse getAgencyMapping(AgencyMappingRequest req);
    DeductionResponse getDeduction(DeductionRequest req);
    ComponentResponse getComponents(ComponentRequest req);
    DdoAllotmentResponse getDdoAllotmentDetails(DdoAllotmentRequest req);
    MotherSanctionResponse getMotherSanctionLimit(MotherSanctionRequest req);
    ErrorMasterResponse getErrorMaster(ErrorMasterRequest req);
    StateResponse getAllStates(); // Added for dynamic portal lookup

    String processDscEnrollmentXml(String xml);
    String processFtoXml(String xml);
}
