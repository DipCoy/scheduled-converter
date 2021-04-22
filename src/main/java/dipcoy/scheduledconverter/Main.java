package dipcoy.scheduledconverter;

import dipcoy.scheduledconverter.converters.PDFToJPEGConverter;
import dipcoy.scheduledconverter.exceptions.converter.ConvertException;
import dipcoy.scheduledconverter.exceptions.converter.LoadFileException;
import dipcoy.scheduledconverter.exceptions.writer.WriterBaseException;
import dipcoy.scheduledconverter.writers.Writer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, LoadFileException, ConvertException, WriterBaseException {
        Scanner in = new Scanner(System.in);
        String pdfPath = in.nextLine();
        in.close();
        File pdf = new File(pdfPath);
        PDFToJPEGConverter converter = new PDFToJPEGConverter(500);
        converter.loadFile(pdf);
        Writer writer = converter.convert();
        writer.write();
        /*Scanner in = new Scanner(System.in);
        String folderPath = in.nextLine();
        in.close();
        File folder = new File(folderPath);
        File[] subFiles = folder.listFiles();
        for (File subF : subFiles) {

        }*/
        /*final ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(5);
            }
        }, 0, 5, TimeUnit.SECONDS);*/
    }
}
