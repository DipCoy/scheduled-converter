package dipcoy.scheduledconverter.filemakers;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.io.FilenameUtils;

public class PDFToJPEGFileMaker implements FileMaker{
    private final File pdf;
    private int currentPage = 0;
    private File jpegDirectory;

    public PDFToJPEGFileMaker(File pdf) {
        this.pdf = pdf;
        jpegDirectory = new File(this.pdf.getParent(), "JPG");
        jpegDirectory.mkdir();
    }

    @Override
    public File nextFile() {
        currentPage++;
        String pdfName = FilenameUtils.removeExtension(pdf.getName());
        String jpegName = pdfName + "_" + currentPage + ".jpeg";
        return new File(jpegDirectory, jpegName);
    }
}
