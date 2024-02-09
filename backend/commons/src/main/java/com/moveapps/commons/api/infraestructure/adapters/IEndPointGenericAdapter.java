package com.moveapps.commons.api.infraestructure.adapters;

import com.moveapps.commons.api.domains.data.ResponseBase;

public interface IEndPointGenericAdapter {
	 ResponseBase toResponseBase(Object object);
}
