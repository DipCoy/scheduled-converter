package dipcoy.scheduledconverter.converters;

import dipcoy.scheduledconverter.writers.Writer;
import dipcoy.scheduledconverter.exceptions.converter.BaseException;

import java.io.File;

public interface Converter {
    void loadFile(File inputFile) throws BaseException;
    Writer convert () throws BaseException;
}
