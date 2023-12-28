package com.nuevospa.task.management.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@Data
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"code", "message"})
public class ResponseService<T> extends BaseResponse{

	private T data;
	private List<T> listData;
	

	public ResponseService(String code, String message) {
		super(code, message);
	}

	public ResponseService(String code, String message, T data) {

		super(code, message);
		this.data = data;
	}
	
	public ResponseService(String code, String message, List<T> listData) {

		super(code, message);
		this.listData = listData;
	}

	
	
	

}
