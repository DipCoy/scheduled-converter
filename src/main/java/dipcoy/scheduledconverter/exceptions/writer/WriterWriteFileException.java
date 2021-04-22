package dipcoy.scheduledconverter.exceptions.writer;

import java.io.File;

public class WriterWriteFileException extends WriterBaseException{
    public WriterWriteFileException(File outputFile) {
        super(String.format("Cannot write to file %s", outputFile.getPath()));
    }

    public WriterWriteFileException(File... outputFiles) {
        super(makeMessage(outputFiles));
    }

    private static String makeMessage(File... outputFiles) {
        StringBuilder messages = new StringBuilder();
        for (File file : outputFiles) {
            messages.append(String.format("Cannot write to file %s \n", file.getPath()));
        }
        return messages.toString();
    }
}
