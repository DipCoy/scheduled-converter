package dipcoy.scheduledconverter.exceptions.converter;

import java.io.File;

public class FileIsNotDirectoryException extends BaseException {
    public FileIsNotDirectoryException(File file) {
        super(String.format("The specified path %s is not directory", file.getPath()));
    }
}
