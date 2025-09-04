package org.example.expresscash.services.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.entity.Sim;
import org.example.expresscash.exceptions.BusinessException;
import org.example.expresscash.mappers.SimMapper;
import org.example.expresscash.model.SearchCriteria;
import org.example.expresscash.model.SimModel;
import org.example.expresscash.repositories.BranchRepository;
import org.example.expresscash.repositories.SimRepository;
import org.example.expresscash.repositories.StatusRepository;
import org.example.expresscash.services.SimService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SimServiceImpl implements SimService {
    private final SimRepository simRepository;
    private final SimMapper simMapper;
    private final StatusRepository statusRepository;
    private final BranchRepository branchRepository;
    @PersistenceContext
    private  EntityManager entityManager;
    @Override
    public void addSim(SimModel simModel) {
        Sim sim = simMapper.toEntity(simModel);
        sim.setStatus(statusRepository.findByCode(simModel.getStatus()).orElse(null));
        sim.setBranch(branchRepository.findByCode(simModel.getBranch().getCode()).orElseThrow(() -> new BusinessException(StatusCodeEnum.BRANCH_NOT_FOUND, "branch not found")));
        simRepository.save(sim);
    }

    @Override
    public void delete(Long userId, Long simId) {
        Sim sim = simRepository.findById(simId)
                .orElseThrow(() -> new BusinessException(
                        StatusCodeEnum.SIM_NOT_FOUND,
                        "SIM number not found"
                ));

        if (!Objects.equals(sim.getBranch().getUserId(), userId)) {
            throw new BusinessException(
                    StatusCodeEnum.UNAUTHORIZED_ACTION,
                    "SIM does not belong to the specified user"
            );
        }

        simRepository.delete(sim);
    }

    @Override
    public List<SimModel> listAllByBranchId(Long branchId) {
        return simRepository.findAllByBranchId(branchId)
                .stream()
                .map(simMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimModel> listAll() {
        return simRepository.findAll()
                .stream()
                .map(simMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimModel> search(SearchCriteria searchCriteria, Pageable pageable) {
        return simRepository.search(searchCriteria, pageable, entityManager)
                .getContent()
                .stream()
                .map(simMapper::toModel)
                .collect(Collectors.toList());
    }
}
