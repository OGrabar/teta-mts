package mts.teta.resizer.console;

import mts.teta.resizer.exceptions.BadAttributesException;
import mts.teta.resizer.validation.AttributesValidator;
import mts.teta.resizer.validation.implementations.ImageProcessorAttributesValidator;
import picocli.CommandLine;

import java.io.File;

public class ConsoleAttributes {

    @CommandLine.Option(
            names = {"--resize", "-r"},
            description = {"New size of image (width, height)"},
            arity = "2..2"
    )
    private Integer resizeValues[];

    @CommandLine.Option(
            names = {"--crop", "-c"},
            description = {"Crop image (width, height, x, y)"},
            arity = "4..4"
    )
    private Integer cropValues[];

    @CommandLine.Option(
            names = {"--quality", "-q"},
            description = {"Quality of output image (from 1 to 100)"}
    )
    protected Integer quality;

    @CommandLine.Option(
            names = {"--blur", "-b"},
            description = {"Reduce image noise detail levels "}
    )
    protected Integer blurRadius;

    @CommandLine.Option(
            names = {"--format ", "-f"},
            description = {"Output image format type"}
    )
    protected String outputFileFormat;

    @CommandLine.Parameters(index = "0")
    protected String inputFilePath;

    @CommandLine.Parameters(index = "1")
    protected String outputFilePath;

    protected Integer resizeHeight;
    protected Integer resizeWidth;

    protected Integer cropHeight;
    protected Integer cropWidth;
    protected Integer x;
    protected Integer y;

    protected File inputFile;
    protected File outputFile;

    protected void initialize() throws BadAttributesException {
        if (resizeValues != null) {
            resizeWidth = resizeValues[0];
            resizeHeight = resizeValues[1];
        }

        if (cropValues != null) {
            cropWidth = cropValues[0];
            cropHeight = cropValues[1];
            x = cropValues[2];
            y = cropValues[3];
        }

        AttributesValidator validator = new ImageProcessorAttributesValidator();
        validator.validate(this);

        inputFile = new File(inputFilePath);
        outputFile = new File(outputFilePath);
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public Integer getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(Integer blurRadius) {
        this.blurRadius = blurRadius;
    }

    public String getOutputFileFormat() {
        return outputFileFormat;
    }

    public void setOutputFileFormat(String outputFileFormat) {
        this.outputFileFormat = outputFileFormat;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }

    public Integer getResizeHeight() {
        return resizeHeight;
    }

    public void setResizeHeight(Integer resizeHeight) {
        this.resizeHeight = resizeHeight;
    }

    public Integer getResizeWidth() {
        return resizeWidth;
    }

    public void setResizeWidth(Integer resizeWidth) {
        this.resizeWidth = resizeWidth;
    }

    public Integer getCropHeight() {
        return cropHeight;
    }

    public void setCropHeight(Integer cropHeight) {
        this.cropHeight = cropHeight;
    }

    public Integer getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(Integer cropWidth) {
        this.cropWidth = cropWidth;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public Integer[] getResizeValues() {
        return resizeValues;
    }

    public void setResizeValues(Integer[] resizeValues) {
        this.resizeValues = resizeValues;
    }

    public Integer[] getCropValues() {
        return cropValues;
    }

    public void setCropValues(Integer[] cropValues) {
        this.cropValues = cropValues;
    }
}
