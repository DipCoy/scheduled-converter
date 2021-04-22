package dipcoy.scheduledconverter;

import dipcoy.scheduledconverter.converters.RecursivePDFToJPEGConverter;
import dipcoy.scheduledconverter.exceptions.converter.FileIsNotDirectoryException;


import java.io.File;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String directoryPath = in.nextLine();
        in.close();
        final ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        s.scheduleAtFixedRate(() -> {
            try {
                new RecursivePDFToJPEGConverter(new File(directoryPath), 500).run();
            } catch (FileIsNotDirectoryException e) {
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.MINUTES);
    }
}
