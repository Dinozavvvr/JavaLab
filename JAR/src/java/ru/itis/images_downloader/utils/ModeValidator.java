package ru.itis.images_downloader.utils;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ModeValidator implements IParameterValidator {
    private static final String ONE_THREAD = "one-thread";
    private static final String MULTI_THREAD = "multi-thread";
    @Override
    public void validate(String name, String value) throws ParameterException {
        if(!(value.equals(ONE_THREAD) || value.equals(MULTI_THREAD))) {
            throw new ParameterException("Parameter " + name + " should be 'one-thread' or 'multi-thread'. You value '" + value + "'");
        }
    }
}