package com.nuevospa.task.management.configuration;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExecuteSQL implements ApplicationRunner{
	
	private final JdbcTemplate jdbcTemplate;

	public ExecuteSQL(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void run(ApplicationArguments args) {
		
		jdbcTemplate.execute("RUNSCRIPT FROM 'classpath:import.sql'");
	}

}
