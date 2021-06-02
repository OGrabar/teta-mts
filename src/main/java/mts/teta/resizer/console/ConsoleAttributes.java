package mts.teta.resizer.console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mts.teta.resizer.operations.Operations;
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
    protected File inputFile;

    @CommandLine.Parameters(index = "1")
    protected File outputFile;

    protected Integer resizeHeight;
    protected Integer resizeWidth;

    protected Integer cropHeight;
    protected Integer cropWidth;
    protected Integer x;
    protected Integer y;

    protected Set<Operations> operations = new HashSet<>();

    protected void initialize() {
        if (resizeValues != null) {
            resizeWidth = resizeValues[0];
            resizeHeight = resizeValues[1];
            operations.add(Operations.RESIZE);
        }

        if (cropValues != null) {
            cropWidth = cropValues[0];
            cropHeight = cropValues[1];
            x = cropValues[2];
            y = cropValues[3];
            operations.add(Operations.CROP);
        }

        if (quality != null) {
            operations.add(Operations.QUALITY);
        }

        if (blurRadius != null) {
            operations.add(Operations.BLUR);
        }

        if (outputFileFormat != null) {
            operations.add(Operations.OUTPUT_FILE_FORMAT);
        }
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
        operations.add(Operations.QUALITY);
    }

    public Integer getBlurRadius() {
        return blurRadius;
    }

    public void setBlurRadius(Integer blurRadius) {
        this.blurRadius = blurRadius;
        operations.add(Operations.BLUR);
    }

    public String getOutputFileFormat() {
        return outputFileFormat;
    }

    public void setOutputFileFormat(String outputFileFormat) {
        this.outputFileFormat = outputFileFormat;
        operations.add(Operations.OUTPUT_FILE_FORMAT);
    }

    public Integer getResizeHeight() {
        return resizeHeight;
    }

    public void setResizeHeight(Integer resizeHeight) {
        this.resizeHeight = resizeHeight;
        operations.add(Operations.RESIZE);
    }

    public Integer getResizeWidth() {
        return resizeWidth;
    }

    public void setResizeWidth(Integer resizeWidth) {
        this.resizeWidth = resizeWidth;
        operations.add(Operations.RESIZE);
    }

    public Integer getCropHeight() {
        return cropHeight;
    }

    public void setCropHeight(Integer cropHeight) {
        this.cropHeight = cropHeight;
        operations.add(Operations.CROP);
    }

    public Integer getCropWidth() {
        return cropWidth;
    }

    public void setCropWidth(Integer cropWidth) {
        this.cropWidth = cropWidth;
        operations.add(Operations.CROP);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
        operations.add(Operations.CROP);
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
        operations.add(Operations.CROP);
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

    public Set<Operations> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operations> operations) {
        this.operations = operations;
    }
}
