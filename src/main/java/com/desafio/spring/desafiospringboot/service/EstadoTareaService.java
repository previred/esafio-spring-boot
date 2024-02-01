package com.desafio.spring.desafiospringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.spring.desafiospringboot.dao.EstadoTareaRepository;
import com.desafio.spring.desafiospringboot.model.EstadoTarea;

@Service
public class EstadoTareaService {

	@Autowired
	private EstadoTareaRepository estadoTareaRepository;
	
	public ResponseEntity<List<EstadoTarea>> obtenerEstadoTareas() {
		try {
			List<EstadoTarea> listaEstadoTareas = new ArrayList<>();
			estadoTareaRepository.findAll().forEach(listaEstadoTareas::add);
			
			if(listaEstadoTareas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<List<EstadoTarea>>(listaEstadoTareas, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
