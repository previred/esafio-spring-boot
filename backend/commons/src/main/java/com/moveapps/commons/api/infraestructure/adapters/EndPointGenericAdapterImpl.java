package com.moveapps.commons.api.infraestructure.adapters;
import static com.moveapps.commons.api.domains.data.ResponseBaseStatusDomain.ERROR;
import static com.moveapps.commons.api.domains.data.ResponseBaseStatusDomain.OK;
import static com.moveapps.commons.api.infraestructure.configs.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.moveapps.commons.api.domains.data.ResponseBase;
import com.moveapps.commons.api.domains.exception.BaseException;

import lombok.SneakyThrows;
@Component
public abstract class EndPointGenericAdapterImpl<D,T> implements IEndPointGenericAdapter {
	@Override
	@SneakyThrows
	public  ResponseBase  toResponseBase(Object object) {
		try {
				List<Object> modelDTOList = new ArrayList();
				if (object != null) {
					if(object instanceof List) {
						List<T> entityList = (List<T>) object;
						if (entityList.size() > 0) {
							modelDTOList = entityList.stream().map(e -> toModelDTO(e))
							.collect(Collectors.toList());
						}
					}else {
						boolean idEntity = false;
						Object modelDTO  = null;
						try {
							modelDTO = toModelDTO((T) object);
							idEntity = true;
						} catch (Exception e) {
							e.fillInStackTrace();
						}
						modelDTOList.add( idEntity ?modelDTO: object);
						
					}
				}
					
				
				
				return new ResponseBase<>().builder()
											.status(OK)
											.message(FINISHED_SUCCESSFULLY)
											.result(modelDTOList)
											.className(className())
											.module(nameModule())
											.build() ;
		}catch (Exception e) {
			throw new BaseException().builder()
									.status(ERROR)
									.message(UNEXPECTED_ERROR)
									.module(nameModule())
									.exception(e)
									.build();
		}
	}
	public abstract String nameModule();
	public abstract String className();
	public abstract D toModelDTO(T entity);
}