package dipcoy.scheduledconverter.exceptions.converter;

import java.io.File;

public class LoadFileException extends BaseException {
    public LoadFileException(File file) {
        super(String.format("Cannot load file %s", file.getPath()));
    }
}
