package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.SimModel;
import org.example.expresscash.services.SimService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("sims")
public class SimResource {
    private final SimService simService;


    @GetMapping
    public List<SimModel> list() {
        return simService.listAll();
    }

    @PostMapping
    public void add(@RequestBody SimModel simModel) {
        simService.addSim(simModel);
    }

    @PostMapping("search")
    public List<SimModel> search(@RequestBody SearchCriteria searchCriteria, Pageable pageable) {
       return simService.search(searchCriteria, pageable);
    }
}
