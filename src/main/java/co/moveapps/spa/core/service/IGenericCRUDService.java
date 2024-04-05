package co.moveapps.spa.core.service;


import co.moveapps.spa.core.exception.BusinessException;

import java.util.List;
import java.util.Optional;

public interface IGenericCRUDService<IN, OUT, K> {

    OUT create(IN object);

    OUT update(IN object);

    Boolean delete(K id) throws BusinessException;

    OUT getById(K id) throws BusinessException;

    List<OUT> getAll(Integer page, Integer size);

}
