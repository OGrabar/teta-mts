package mts.teta.resizer;

import mts.teta.resizer.console.ConsoleAttributes;
import mts.teta.resizer.exceptions.BadAttributesException;
import mts.teta.resizer.imageprocessor.ImageProcessor;
import mts.teta.resizer.validation.AttributesValidator;
import mts.teta.resizer.validation.implementations.ImageProcessorAttributesValidator;
import picocli.CommandLine;

import javax.imageio.ImageIO;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "resizer", mixinStandardHelpOptions = true, version = "resizer 0.0.1", description = "...")
public class ResizerApp extends ConsoleAttributes implements Callable<Integer> {
    public static void main(String... args) {
        int exitCode = runConsole(args);
        System.exit(exitCode);
    }

    protected static int runConsole(String[] args) {
        return new CommandLine(new ResizerApp()).execute(args);
    }

    @Override
    public Integer call() throws Exception {
        initialize();
        AttributesValidator validator = new ImageProcessorAttributesValidator();
        validator.validate(this);
        ImageProcessor imageProcessor = new ImageProcessor();
        imageProcessor.processImage(ImageIO.read(inputFile), this);
        return 0;
    }
}
