package mts.teta.resizer.validation.implementations;

import mts.teta.resizer.console.ConsoleAttributes;
import mts.teta.resizer.exceptions.BadAttributesException;
import mts.teta.resizer.validation.AttributesValidator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class ImageProcessorAttributesValidator implements AttributesValidator {
    private static final String JPEG = "JPEG";
    private static final String PNG = "PNG";
    private final List<String> outputFileFormats = new ArrayList<String>() {
        {
            add(JPEG);
            add(PNG);
        }
    };

    private String errorMessage = "";
    private boolean isValid = true;

    @Override
    public boolean validate(ConsoleAttributes consoleAttributes) throws BadAttributesException {
        validateFilePaths(consoleAttributes);
        if (consoleAttributes.getQuality() != null) {
            validateQuality(consoleAttributes);
        }
        if (consoleAttributes.getResizeValues() != null) {
            validateResizeValues(consoleAttributes);
        }
        if (consoleAttributes.getCropValues() != null) {
            validateCropValues(consoleAttributes);
        }
        if (consoleAttributes.getBlurRadius() != null) {
            validateBlurRadius(consoleAttributes);
        }
        if (consoleAttributes.getOutputFileFormat() != null) {
            validateOutputFileFormat(consoleAttributes);
        }

        if (!isValid) {
            throw new BadAttributesException(errorMessage);
        }
        return true;
    }

    private void validateOutputFileFormat(ConsoleAttributes consoleAttributes) {
        if (outputFileFormats.contains(consoleAttributes.getOutputFileFormat())) {
            errorMessage += format("Output file format should be one of %s, now is %d\n", outputFileFormats.toString(),
                    consoleAttributes.getBlurRadius());
            isValid = false;
        }
    }

    private void validateBlurRadius(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getBlurRadius() < 1 || consoleAttributes.getBlurRadius() > 100) {
            errorMessage += format("Blur radius should be from 1 to 100, now is %d\n", consoleAttributes.getBlurRadius());
            isValid = false;
        }
    }

    private void validateCropValues(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getCropHeight() < 1 || consoleAttributes.getCropWidth() < 1 ||
                consoleAttributes.getX() < 0 || consoleAttributes.getY() < 0) {
            errorMessage += format(
                    "Crop width and height should be from 1 to 100, now width is %d, height is %d\n" +
                            "x and y should be greater than 0, now x is %d. y is %d\n", consoleAttributes.getCropWidth(),
                    consoleAttributes.getCropHeight(), consoleAttributes.getX(), consoleAttributes.getY()
            );
            isValid = false;
        }
    }

    private void validateResizeValues(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getResizeHeight() < 1 || consoleAttributes.getResizeWidth() < 1) {
            errorMessage += format("Resize width and height should be from 1 to 100, now width is %d, height is %d\n",
                    consoleAttributes.getResizeWidth(), consoleAttributes.getResizeHeight());
            isValid = false;
        }
    }

    private void validateQuality(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getQuality() < 1 || consoleAttributes.getQuality() > 100) {
            errorMessage += format("Quality should be from 1 to 100, now is %d\n", consoleAttributes.getQuality());
            isValid = false;
        }
    }

    private void validateFilePaths(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getInputFilePath().isEmpty()) {
            errorMessage += "Input file path is empty\n";
            isValid = false;
        }
        if (consoleAttributes.getOutputFilePath().isEmpty()) {
            errorMessage += "Output file path is empty\n";
            isValid = false;
        }
    }
}
