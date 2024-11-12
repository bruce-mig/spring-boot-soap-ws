package com.github.bruce_mig.spring_boot_soap_ws.service;

import com.github.bruce_mig.spring_boot_soap_ws.loan_eligibility.Acknowledgement;
import com.github.bruce_mig.spring_boot_soap_ws.loan_eligibility.CustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanEligibilityService {

    public Acknowledgement checkLoanEligibility(CustomerRequest request){
        Acknowledgement acknowledgement = new Acknowledgement();
        List<String> mismatchList = getMismatchList(request, acknowledgement);

        if (!mismatchList.isEmpty()){
            acknowledgement.setApprovedAmount(0);
            acknowledgement.setIsEligible(false);
        } else {
            acknowledgement.setApprovedAmount(5000);
            acknowledgement.setIsEligible(true);
            mismatchList.clear();
        }

        return acknowledgement;
    }

    private static List<String> getMismatchList(CustomerRequest request, Acknowledgement acknowledgement) {
        List<String> mismatchList = acknowledgement.getCriteriaMismatch();

        if (!(request.getAge() > 30 && request.getAge() < 60)) {
            mismatchList.add("Person age should be between 30 to 60");
        }

        if (!(request.getYearlyIncome() > 3000)) {
            mismatchList.add("Minimum yearly income should be more than USD$3000");
        }

        if (!(request.getYearlyIncome() > 500)) {
            mismatchList.add("Low Credit score, please try again after 6 months");
        }
        return mismatchList;
    }
}
