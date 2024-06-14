package com.desafio.spring.ec.bs.services;

import com.desafio.spring.ec.bs.repository.StatusRepository;
import com.desafio.spring.ec.bs.utils.GenericConverterUtils;
import com.desafio.spring.ec.ds.dto.StatusTO;
import com.desafio.spring.ec.ds.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements IStatusService{

    private final StatusRepository statusRepository;
    private final GenericConverterUtils genericConverterUtils;

    @Override
    public StatusTO save(StatusTO statusTO) {
        return null;
    }

    @Override
    public StatusTO update(Long aLong, StatusTO statusTO) {
        return null;
    }

    @Override
    public List<StatusTO> findAll() {
        List<Status> statusList = statusRepository.findAll();
        return genericConverterUtils.convertListToListDto(statusList, StatusTO.class);
    }

    @Override
    public StatusTO findById(Long aLong) throws Exception {
        Status status = statusRepository.findById(aLong).orElseThrow(() -> new Exception("ID NOT FOUND: " + aLong));
        return genericConverterUtils.convertToDto(status, StatusTO.class);
    }

    @Override
    public void delete(Long aLong) {

    }
}
