package com.github.bruce_mig.spring_boot_soap_ws.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.github.bruce_mig.spring_boot_soap_ws.loan_eligibility.Acknowledgement;
import com.github.bruce_mig.spring_boot_soap_ws.loan_eligibility.CustomerRequest;
import com.github.bruce_mig.spring_boot_soap_ws.service.LoanEligibilityService;

@Endpoint
public class LoanEligibilityIndicatorEndpoint {

    private static final String NAMESPACE = "http://www.github.com/bruce_mig/spring_boot_soap_ws/loan_ligibility";
    private final LoanEligibilityService service;

    public LoanEligibilityIndicatorEndpoint(LoanEligibilityService service) {
        this.service = service;
    }

    @PayloadRoot(namespace=NAMESPACE, localPart="CustomerRequest")
    @ResponsePayload
    public Acknowledgement getLoanStatus(@RequestPayload CustomerRequest request) {
        return service.checkLoanEligibility(request);
    }
}
