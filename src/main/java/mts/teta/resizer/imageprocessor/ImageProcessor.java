package mts.teta.resizer.imageprocessor;

import mts.teta.resizer.ResizerApp;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessor {


    public void processImage(BufferedImage read, ResizerApp resizerApp) throws IOException {
        if ()
    }

    private void a(BufferedImage read, ResizerApp resizerApp) throws IOException {
        Thumbnails.of(read)
                .size(resizerApp.getResizeWidth(), resizerApp.getResizeHeight())
                .outputQuality(resizerApp.getQuality() / 100d)
                .toFile(resizerApp.getOutputFile());
    }
}
