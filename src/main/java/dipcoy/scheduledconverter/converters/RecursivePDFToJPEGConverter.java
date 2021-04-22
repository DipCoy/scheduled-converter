package dipcoy.scheduledconverter.converters;

import dipcoy.scheduledconverter.exceptions.converter.ConvertException;
import dipcoy.scheduledconverter.exceptions.converter.FileIsNotDirectoryException;
import dipcoy.scheduledconverter.exceptions.converter.LoadFileException;
import dipcoy.scheduledconverter.exceptions.writer.WriterBaseException;
import dipcoy.scheduledconverter.writers.Writer;

import java.io.File;
import java.util.Locale;
import java.util.Objects;

public class RecursivePDFToJPEGConverter implements Runnable {
    private final File directory;
    private final int DPI;

    public RecursivePDFToJPEGConverter(File directory, int dpi) throws FileIsNotDirectoryException {
        if (!directory.isDirectory()) {
            throw new FileIsNotDirectoryException(directory);
        }
        this.directory = directory;
        DPI = dpi;
    }

    @Override
    public void run() {
        convertDirectoryFiles(directory);
        System.out.println("Complete");
    }

    private void convertDirectoryFiles(File convertDirectory) {
        File[] pdfs = convertDirectory.listFiles((dir, name) -> name
                .toLowerCase(Locale.ROOT)
                .endsWith(".pdf"));

        for (File pdf : pdfs) {
            try (PDFToJPEGConverter converter = new PDFToJPEGConverter(DPI)) {
                try {
                    converter.loadFile(pdf);
                    Writer writer = converter.convert();
                    writer.write();
                } catch (LoadFileException | ConvertException | WriterBaseException e) {
                    System.out.println(e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        File[] dirs = Objects.requireNonNull(convertDirectory.listFiles(File::isDirectory));
        for (File dir : dirs) {
            convertDirectoryFiles(dir);
        }
    }
}
