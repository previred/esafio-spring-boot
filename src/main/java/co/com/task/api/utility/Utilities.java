package co.com.task.api.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.com.task.api.config.ConfigApplication;
import co.com.task.api.domain.Session;
import co.com.task.api.domain.Task;
import co.com.task.api.domain.User;
import co.com.task.api.dto.TaskDTO;
import co.com.task.api.exceptions.ExceptionManager;
import co.com.task.api.mapper.TaskMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Utilities {

    private ConfigApplication config;

    public Utilities(ConfigApplication config) {
	this.config = config;
    }

    public static void validate(Object object, Validator validator, String mensaje) {
	validationObjet(object, mensaje);
	Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
	if (!constraintViolations.isEmpty()) {
	    StringBuilder strMessage = new StringBuilder();
	    constraintViolations.forEach(violation -> strMessage
		    .append(violation.getPropertyPath().toString() + " - " + violation.getMessage() + ". \n"));
	    throw new ExceptionManager().new NotValidParamException(strMessage.toString());
	}
    }

    public static void validationObjet(Object objeto, String mensaje) {
	if (!Optional.ofNullable(objeto).isPresent()) {
	    throw new ExceptionManager().new NullEntityExcepcion(mensaje);
	}
    }

    public static String toStringObjec(Object objeto) {
	ObjectMapper mapper = new ObjectMapper();
	mapper.enable(SerializationFeature.INDENT_OUTPUT);
	mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	mapper.registerModule(new JavaTimeModule());
	mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	try {
	    return mapper.writeValueAsString(objeto);
	} catch (JsonProcessingException e) {
	    log.error(e.getMessage());
	    return objeto.getClass().getName() + "@" + Integer.toHexString(objeto.hashCode());
	}
    }

    public static List<Task> mapTask(List<TaskDTO> listTasks, UUID idUser) {
	if (listTasks == null || listTasks.isEmpty()) {
	    return new ArrayList<>();
	}
	listTasks.forEach(taskDto -> taskDto.setIdUser(idUser));
	return StreamSupport.stream(listTasks.spliterator(), false).map(TaskMapper::taskDtoToTaskDomain).toList();

    }

    public static String convertToDateViaInstant(LocalDateTime dateToConvert) {
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	return simpleDateFormat.format(date);
    }

    public static Session createSession(User user, String token) {
	return Session.builder()
		.isActive(false)
		.lastLogin(LocalDateTime.now())
		.token(token)
		.user(user)
		.build();
    }

    public boolean isValidEmail(String email) {
	Pattern pattern = Pattern.compile(config.getEmailRegx());
	Matcher matcher = pattern.matcher(email);
	return matcher.matches();
    }

    public boolean isValidPassword(String password) {
	Pattern pattern = Pattern.compile(config.getPasswordRegx());
	Matcher matcher = pattern.matcher(password);
	return matcher.matches();
    }

}
