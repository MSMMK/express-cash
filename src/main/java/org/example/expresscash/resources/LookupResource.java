package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.LookupModel;
import org.example.expresscash.services.CityService;
import org.example.expresscash.services.GovernorateService;
import org.example.expresscash.services.TransactionTypeLookupService;
import org.example.expresscash.services.UserTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lookups")
@RequiredArgsConstructor
public class LookupResource {
    private final CityService cityService;
    private final GovernorateService governorateService;
    private final UserTypeService userTypeService;
    private final TransactionTypeLookupService transactionTypeLookupService;


    @GetMapping("governorates")
    public List<LookupModel> listGovernorates(){
        return governorateService.findAll();
    }

    @GetMapping("governorates/{govId}/cities")
    public List<LookupModel> listGovernorateCities(@PathVariable Long govId) {
        return cityService.listByGovId(govId);
    }

    @GetMapping("user-types")
    public List<LookupModel> listUserTypes(){
        return userTypeService.list();
    }

    @GetMapping("transaction-types")
    public List<LookupModel> listTransactionTypes(){
        return transactionTypeLookupService.listAll();
    }
}
