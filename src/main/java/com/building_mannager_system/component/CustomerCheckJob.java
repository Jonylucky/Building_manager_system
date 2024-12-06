package com.building_mannager_system.component;

import com.building_mannager_system.service.customer_service.ContractService;
import com.building_mannager_system.service.customer_service.CustomerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerCheckJob implements Job {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ContractService contractService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
      //check birthday for cusstomer
        contractService.checkCustomerBirthday();
        //check contractTypedocument
        contractService.checkEndDateContract();


        //check Customertypedocument(hợp dồng con thiếu typedocument)
        contractService.checkInactiveContractsAndDocuments();


    }

}
