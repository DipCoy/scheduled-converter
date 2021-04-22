package dipcoy.scheduledconverter.filemakers;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

public class SinglePagePDFToJPEGFileMaker implements FileMaker {
    private final File pdf;
    private final File jpegDirectory;

    public SinglePagePDFToJPEGFileMaker(File pdf) {
        this.pdf = pdf;
        jpegDirectory = new File(this.pdf.getParent(), "JPG");
        jpegDirectory.mkdir();
    }

    @Override
    public File nextFile() {
        String pdfName = FilenameUtils.removeExtension(pdf.getName());
        String jpegName = pdfName + ".jpeg";
        return new File(jpegDirectory, jpegName);
    }
}
