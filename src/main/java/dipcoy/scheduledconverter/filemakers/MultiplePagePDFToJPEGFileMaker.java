package dipcoy.scheduledconverter.filemakers;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class MultiplePagePDFToJPEGFileMaker implements FileMaker {
    private final File pdf;
    private int currentPage = 0;
    private final File jpegDirectory;

    public MultiplePagePDFToJPEGFileMaker(File pdf) {
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
