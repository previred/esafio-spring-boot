package com.moveapps.commons.api.domains.data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T> {
	private String status = "";
	private String message = "";
	private String className = "";
	private T result = (T) "";
	private String module = "";

}