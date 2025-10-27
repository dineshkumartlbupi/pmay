package com.aparsh.api.pmay.service;

import com.aparsh.api.pmay.dto.requests.*;
import com.aparsh.api.pmay.dto.responses.*;
import com.aparsh.api.pmay.model.StateEntity;
import com.aparsh.api.pmay.repository.StateRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@Service
public class IntegrationServiceImpl implements IntegrationService {

    private final StateRepository stateRepository;

    private final String DEFAULT_SLSCODE = "UP23";
    private final String DEFAULT_CSSCODE = "9180";

    public IntegrationServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public StateResponse getState(StateRequest req) {
        StateResponse resp = new StateResponse();
        StateResponse.StateResult st = new StateResponse.StateResult();
        st.setState_Code("UP");
        st.setState_Name("Uttar Pradesh");
        st.setShort_Name("UP");
        st.setLGD_State_Code("09");
        st.setSt_local_name("उत्तर प्रदेश");
        resp.setStatus(true);
        resp.setResult(List.of(st));
        return resp;
    }

    @Override
    public SnaDetailsResponse getSnaDetails(SnaDetailsRequest req) {
        SnaDetailsResponse r = new SnaDetailsResponse();
        SnaDetailsResponse.SnaDtl d = new SnaDetailsResponse.SnaDtl();
        d.setSLSCODE(DEFAULT_SLSCODE);
        d.setSNA("Sample SNA for CSS " + DEFAULT_CSSCODE);
        d.setCSSCODE(DEFAULT_CSSCODE);
        d.setCENTERSHARE("60");
        r.setSnaDtl(List.of(d));
        return r;
    }

    @Override public AgencyMappingResponse getAgencyMapping(AgencyMappingRequest req) {
        AgencyMappingResponse r = new AgencyMappingResponse();
        r.setStatus(true);
        r.setResult(List.of(new AgencyMappingResponse.AgencyMappingResult("AG001", DEFAULT_SLSCODE)));
        return r;
    }

    @Override public DeductionResponse getDeduction(DeductionRequest req) {
        DeductionResponse r = new DeductionResponse();
        r.setStatus(true);
        r.setResult(List.of(new DeductionResponse.DeductionResult("DED001","Sample Deduction")));
        return r;
    }

    @Override public ComponentResponse getComponents(ComponentRequest req) {
        ComponentResponse r = new ComponentResponse();
        r.setStatus(true);
        r.setComponents(List.of(new ComponentResponse.Component("CMP001","Component A")));
        return r;
    }

    @Override public DdoAllotmentResponse getDdoAllotmentDetails(DdoAllotmentRequest req) {
        DdoAllotmentResponse r = new DdoAllotmentResponse();
        r.setStatus(true);
        r.setResult(List.of(new DdoAllotmentResponse.DdoAllotment("DDO001","100000")));
        return r;
    }

    @Override public MotherSanctionResponse getMotherSanctionLimit(MotherSanctionRequest req) {
        MotherSanctionResponse r = new MotherSanctionResponse();
        r.setStatus(true);
        r.setResult(List.of(new MotherSanctionResponse.MotherSanction("M001","50000")));
        return r;
    }

    @Override public ErrorMasterResponse getErrorMaster(ErrorMasterRequest req) {
        ErrorMasterResponse r = new ErrorMasterResponse();
        r.setStatus(true);
        r.setResult(List.of(new ErrorMasterResponse.ErrorMaster("EXP0000","Success")));
        return r;
    }

    @Override
    public String processDscEnrollmentXml(String xml) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(com.aparsh.api.pmay.xml.DscEnrollmentRequest.class);
            Unmarshaller un = ctx.createUnmarshaller();
            StringReader sr = new StringReader(xml);
            com.aparsh.api.pmay.xml.DscEnrollmentRequest req = (com.aparsh.api.pmay.xml.DscEnrollmentRequest) un.unmarshal(sr);

            // Build ack
            com.aparsh.api.pmay.xml.DscEnrollmentAck ack = new com.aparsh.api.pmay.xml.DscEnrollmentAck();
            ack.setStatus("SUCCESS");
            ack.setMessage("Received DSC enrollment for " + req.getApplicantName());

            JAXBContext outCtx = JAXBContext.newInstance(com.aparsh.api.pmay.xml.DscEnrollmentAck.class);
            Marshaller m = outCtx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(ack, sw);
            return sw.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "<error>Invalid XML</error>";
        }
    }

    @Override
    public String processFtoXml(String xml) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(com.aparsh.api.pmay.xml.FtoRequest.class);
            Unmarshaller un = ctx.createUnmarshaller();
            StringReader sr = new StringReader(xml);
            com.aparsh.api.pmay.xml.FtoRequest req = (com.aparsh.api.pmay.xml.FtoRequest) un.unmarshal(sr);

            com.aparsh.api.pmay.xml.FtoAck ack = new com.aparsh.api.pmay.xml.FtoAck();
            ack.setStatus("SUCCESS");
            ack.setMessage("FTO Received: " + req.getFtoNumber());

            JAXBContext outCtx = JAXBContext.newInstance(com.aparsh.api.pmay.xml.FtoAck.class);
            Marshaller m = outCtx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            m.marshal(ack, sw);
            return sw.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "<error>Invalid FTO XML</error>";
        }
    }
}
