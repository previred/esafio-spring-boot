package com.nuevospa.task.management.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactUserDto {
	
	
	private long number;
	
	private int citycode;
	
	private String contrycode;

}
