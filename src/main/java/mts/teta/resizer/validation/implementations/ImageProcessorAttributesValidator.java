package mts.teta.resizer.validation.implementations;

import mts.teta.resizer.operations.Operations;
import mts.teta.resizer.console.ConsoleAttributes;
import mts.teta.resizer.exceptions.BadAttributesException;
import mts.teta.resizer.validation.AttributesValidator;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;

public class ImageProcessorAttributesValidator implements AttributesValidator {
    private static final String JPEG = "JPEG";
    private static final String PNG = "PNG";
    private static final String ERROR_MESSAGE = "Please check params!";

    private final List<String> outputFileFormats = new ArrayList<String>() {
        {
            add(JPEG);
            add(PNG);
        }
    };

    private boolean isValid = true;

    @Override
    public boolean validate(ConsoleAttributes consoleAttributes) throws BadAttributesException {
        Set<Operations> operations = consoleAttributes.getOperations();
        operations.forEach(operation -> validateOperationAttributes(operation, consoleAttributes));

        if (!isValid) {
            throw new BadAttributesException(ERROR_MESSAGE);
        }
        return true;
    }

    private void validateOperationAttributes(Operations operation, ConsoleAttributes consoleAttributes) {
        switch (operation) {
            case BLUR: validateBlurRadius(consoleAttributes); break;
            case QUALITY: validateQuality(consoleAttributes); break;
            case CROP: validateCropValues(consoleAttributes); break;
            case RESIZE: validateResizeValues(consoleAttributes); break;
            case OUTPUT_FILE_FORMAT: validateOutputFileFormat(consoleAttributes); break;
        }
    }

    private void validateOutputFileFormat(ConsoleAttributes consoleAttributes) {
        boolean isValidOutputFormat = outputFileFormats.stream()
                .anyMatch(format -> format.equalsIgnoreCase(consoleAttributes.getOutputFileFormat()));
        if (!isValidOutputFormat) {
            isValid = false;
        }
    }

    private void validateBlurRadius(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getBlurRadius() < 0) {
            isValid = false;
        }
    }

    private void validateCropValues(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getCropHeight() < 1 || consoleAttributes.getCropWidth() < 1) {
            isValid = false;
        }
    }

    private void validateResizeValues(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getResizeHeight() < 1 || consoleAttributes.getResizeWidth() < 1) {
            isValid = false;
        }
    }

    private void validateQuality(ConsoleAttributes consoleAttributes) {
        if (consoleAttributes.getQuality() < 1 || consoleAttributes.getQuality() > 100) {
            isValid = false;
        }
    }
}
