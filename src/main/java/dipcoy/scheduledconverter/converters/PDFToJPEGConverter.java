package dipcoy.scheduledconverter.converters;

import dipcoy.scheduledconverter.exceptions.converter.ConvertException;
import dipcoy.scheduledconverter.filemakers.FileMaker;
import dipcoy.scheduledconverter.filemakers.MultiplePagePDFToJPEGFileMaker;
import dipcoy.scheduledconverter.filemakers.SinglePagePDFToJPEGFileMaker;
import dipcoy.scheduledconverter.writers.JPEGWriter;
import dipcoy.scheduledconverter.writers.Writer;
import dipcoy.scheduledconverter.exceptions.converter.LoadFileException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFToJPEGConverter implements Converter, AutoCloseable {
    private PDDocument document;
    private PDFRenderer renderer;
    private File inputFile;
    private final int DPI;

    public PDFToJPEGConverter(int dpi) {
        DPI = dpi;
    }

    private boolean isValid(File file) throws LoadFileException {
        return true;
        // TODO: PreflightParser throws Error;
        /*ValidationResult result;
        try {
            PreflightParser parser = new PreflightParser(file);
            parser.parse();
            PreflightDocument document = parser.getPreflightDocument();
            document.validate();
            result = document.getResult();
            document.close();
        } catch (SyntaxValidationException e) {
            result = e.getResult();
        } catch (IOException e) {
            throw new LoadFileException(file);
        }

        return result.isValid();*/
    }

    @Override
    public void loadFile(File inputFile) throws LoadFileException {
        this.inputFile = inputFile;
        if (!isValid(inputFile)) {
            throw new LoadFileException(inputFile);
        }

        try {
            document = PDDocument.load(inputFile);
        } catch (IOException e) {
            throw new LoadFileException(inputFile);
        }
        renderer = new PDFRenderer(document);
    }

    @Override
    public Writer convert() throws ConvertException {
        JPEGWriter writer = new JPEGWriter();
        FileMaker fileMaker;
        if (document.getNumberOfPages() == 1) {
            fileMaker = new SinglePagePDFToJPEGFileMaker(inputFile);
        } else {
            fileMaker = new MultiplePagePDFToJPEGFileMaker(inputFile);
        }
        BufferedImage bufferedImage;
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            try {
                bufferedImage = renderer.renderImageWithDPI(page, DPI);
            } catch (IOException e) {
                throw new ConvertException(inputFile);
            }
            writer.addImage(bufferedImage, fileMaker.nextFile());
        }

        return writer;
    }

    @Override
    public void close() throws Exception {
        document.close();
    }
}
