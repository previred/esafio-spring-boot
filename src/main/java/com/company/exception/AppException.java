package com.company.exception;


import com.company.exception.enums.CodeExceptions;
import com.company.util.PropertiesUtil;
import lombok.Getter;

@Getter
public class AppException  extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String codError;


    public AppException(final CodeExceptions codError) {
        super(new PropertiesUtil().getValueException(codError.getValue()));
        this.codError = codError.getValue();
    }

}