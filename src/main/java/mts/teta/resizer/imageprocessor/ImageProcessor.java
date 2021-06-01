package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvinplugins.MarvinPluginCollection;
import mts.teta.resizer.ResizerApp;
import mts.teta.resizer.operations.Operations;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageProcessor {

    private static final String DEFAULT_OUTPUT_FILE_FORMAT = "JPEG";
    private Builder<BufferedImage> builder;

    public void processImage(BufferedImage read, ResizerApp resizerApp) throws IOException {
        read = gaussianBlur(read, resizerApp);
        read = crop(read, resizerApp);
        builder = Thumbnails.of(read);
        resize(read, resizerApp);
        setQuality(resizerApp);
        String outputFileFormat = resizerApp.getOutputFileFormat() != null ? resizerApp.getOutputFileFormat().toUpperCase()
                : DEFAULT_OUTPUT_FILE_FORMAT;
        ImageIO.write(builder.asBufferedImage(), outputFileFormat, resizerApp.getOutputFile());
    }

    private void resize(BufferedImage read, ResizerApp resizerApp) {
        if (resizerApp.getOperations().contains(Operations.RESIZE)) {
            builder.forceSize(resizerApp.getResizeWidth(), resizerApp.getResizeHeight());
        } else {
            builder.size(read.getWidth(), read.getHeight());
        }
    }

    private void setQuality(ResizerApp resizerApp) {
        if (resizerApp.getOperations().contains(Operations.QUALITY)) {
            builder.outputQuality(resizerApp.getQuality() / 100d);
        }
    }

    private BufferedImage gaussianBlur(BufferedImage read, ResizerApp resizerApp) {
        if (resizerApp.getOperations().contains(Operations.BLUR)) {
            MarvinImage image = new MarvinImage(read);
            MarvinPluginCollection.gaussianBlur(image, image, resizerApp.getBlurRadius());
            return image.getBufferedImageNoAlpha();
        } else {
            return read;
        }
    }

    private BufferedImage crop(BufferedImage read, ResizerApp resizerApp) {
        if (resizerApp.getOperations().contains(Operations.CROP)) {
            MarvinImage image = new MarvinImage(read);
            MarvinPluginCollection.crop(image.clone(), image, resizerApp.getX(), resizerApp.getY(), resizerApp.getCropWidth(),
                    resizerApp.getCropHeight());
            return image.getBufferedImageNoAlpha();
        } else {
            return read;
        }
    }
}
