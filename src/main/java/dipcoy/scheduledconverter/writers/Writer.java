package dipcoy.scheduledconverter.writers;

import dipcoy.scheduledconverter.exceptions.writer.WriterBaseException;

public interface Writer {
    void write() throws WriterBaseException;
}
