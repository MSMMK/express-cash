package org.example.expresscash.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.entity.Branch;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.mappers.BranchMapper;
import org.example.expresscash.model.BranchModel;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.repositories.BranchRepository;
import org.example.expresscash.services.AuthService;
import org.example.expresscash.services.BranchService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final AuthService authService;
    private final BranchMapper branchMapper;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addBranch(BranchModel branchModel) {
        Branch branch = branchMapper.toEntity(branchModel);
        branch.setUserId(authService.authUser().getId());
        branch.setCode(UUID.randomUUID().toString());
        branchRepository.save(branch);
    }

    @Override
    public List<BranchModel> search(SearchCriteria searchCriteria, Pageable pageable) {
        return branchRepository.search(searchCriteria, entityManager, pageable)
                .getContent();
    }

    @Override
    public void deleteBranch(String branchCode) {
        Optional<Branch> branch = branchRepository.findByCode(branchCode);
        if (branch.isPresent() && branch.get().getUserId().equals(authService.authUser().getId())) {
            branchRepository.delete(branch.get());
            return;
        }
       throw new BusinessException(StatusCodeEnum.BRANCH_NOT_FOUND, "branch with code {} Not found", branchCode);
    }
}
