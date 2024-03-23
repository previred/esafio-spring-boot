package com.reto.tecnico.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.Arrays;

public class Util {
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper sourceWrapper = new BeanWrapperImpl(source);
        return Arrays.stream(sourceWrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> sourceWrapper.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);

    }

}