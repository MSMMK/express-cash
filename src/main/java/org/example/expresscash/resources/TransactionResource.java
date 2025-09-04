package org.example.expresscash.resources;

import lombok.RequiredArgsConstructor;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.TransactionHistoryModel;
import org.example.expresscash.services.TransactionHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionResource {
    private final TransactionHistoryService transactionHistoryService;

    @PostMapping
    public void create(@RequestBody TransactionHistoryModel transactionHistoryModel) {
        transactionHistoryService.createTransaction(transactionHistoryModel);
    }

    @PostMapping("search")
    public List<TransactionHistoryModel> search(@RequestBody SearchCriteria searchCriteria, Pageable pageable)  {
        return transactionHistoryService.listTransactions(searchCriteria, pageable);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        transactionHistoryService.deleteTransaction(id);
    }
}
