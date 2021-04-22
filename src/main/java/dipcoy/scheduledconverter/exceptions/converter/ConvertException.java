package dipcoy.scheduledconverter.exceptions.converter;

import java.io.File;

public class ConvertException extends BaseException {
    public ConvertException(File file) {
        super(String.format("Cannot convert file %s", file.getPath()));
    }
}
