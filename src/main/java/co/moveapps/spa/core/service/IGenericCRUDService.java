package co.moveapps.spa.core.service;


import co.moveapps.spa.core.exception.BusinessException;

import javax.transaction.Transactional;
import java.util.List;

public interface IGenericCRUDService<IN, OUT, K> {

    @Transactional
    OUT create(IN request) throws BusinessException;

    OUT update(K id, IN request) throws BusinessException;

    Boolean delete(K id) throws BusinessException;

    OUT getById(K id) throws BusinessException;

    List<OUT> getAll(Integer page, Integer size);

}
