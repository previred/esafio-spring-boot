package com.company.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesUtil {

    public static final String EXCEPCION_PROPERTIES = "exception.properties";


    public String getValueException(final String code) {
        return getPropValues(EXCEPCION_PROPERTIES, code);
    }


    public  String getPropValues(String fileName, String code)  {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try {
            Properties prop = new Properties();
            prop.load(inputStream);
            return  prop.getProperty(code);
        } catch (Exception e) {
            return StringUtils.empty;
        } finally {
            if(inputStream!=null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


}