package com.moveapps.commons.api.domains.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moveapps.commons.api.domains.exception.BaseException;

import static com.moveapps.commons.api.domains.data.ResponseBaseStatusDomain.*;
import static com.moveapps.commons.api.infraestructure.configs.Constants.*;

import org.springframework.data.jpa.repository.JpaRepository;
import lombok.SneakyThrows;
@Service
public abstract class EndPointServiceImpl< D , T, ID>   implements IEndPointService<T, ID>{
	
	@Override
	@SneakyThrows
	public T save(T entity) {
		try {
	       	return getDao().save(entity);
		} catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
		
	}
	
	@Override
	@SneakyThrows
	public List<T> saveAll(List<T> entityList) {
		try {
			List<T>  entityListResult = this.getDao().saveAll(entityList);
			
			return entityListResult;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T update(T entity) {
		try {
			return getDao().save(entity);
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	@Override
	@SneakyThrows
	public T delete(ID id) {
		try {
			T entity = getById(id);
			entity = statusChangeDelete(entity);
			return this.update(entity);
		
		}catch (Exception e) {
				throw new BaseException().builder()
										.status(ERROR)
										.message(UNEXPECTED_ERROR)
										.module(nameModule())
										.exception(e)
										.build();
		}
	}

	@Override
	@SneakyThrows
	public T getById(ID id) {
		try {	
			Optional<T> obj = getDao().findById(id);
			if (obj.isPresent()) {
				return obj.get();
			}
			return null;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
	}
	}

	@Override
	@SneakyThrows
	public List<T> getAll() {
		try {	
			List<T> returnList = new ArrayList<>();
			getDao().findAll().forEach(obj -> returnList.add(obj));
			return returnList;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}

	

	public abstract JpaRepository<T, ID> getDao();
	public abstract T statusChangeDelete(T entity);
	public abstract String nameModule();
	public abstract String className();
	
}