package dipcoy.scheduledconverter.writers;

import dipcoy.scheduledconverter.exceptions.writer.WriterBaseException;
import dipcoy.scheduledconverter.exceptions.writer.WriterWriteFileException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class JPEGWriter implements Writer {
    private final ArrayList<ImageAndFile> imagesAndFiles = new ArrayList<>();

    public void addImage(BufferedImage image, File outputFile) {
        imagesAndFiles.add(new ImageAndFile(image, outputFile));
    }

    @Override
    public void write() throws WriterBaseException {
        ArrayList<File> errorFiles = new ArrayList<>();
        for (ImageAndFile imageAndFile : imagesAndFiles) {
            try {
                ImageIO.write(imageAndFile.image, "JPEG", imageAndFile.file);
            } catch (IOException e) {
                errorFiles.add(imageAndFile.file);
            }
        }
        if (!errorFiles.isEmpty())
            throw new WriterWriteFileException(errorFiles.toArray(new File[0]));
    }
}

final class ImageAndFile {
    final BufferedImage image;
    final File file;

    ImageAndFile(BufferedImage image, File file) {
        this.image = image;
        this.file = file;
    }
}