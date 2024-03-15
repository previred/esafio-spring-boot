package com.nuevospa.desafiospringboot.converter;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Converter {
	
	private static ModelMapper modelMapper;
	
	private Converter() {
		
	}
	
	public static ModelMapper getMapper() {
		if(Objects.isNull(modelMapper)) {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		}
		return modelMapper;
	}

}
