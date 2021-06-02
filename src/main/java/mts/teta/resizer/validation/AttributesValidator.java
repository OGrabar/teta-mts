package mts.teta.resizer.validation;

import mts.teta.resizer.console.ConsoleAttributes;
import mts.teta.resizer.exceptions.BadAttributesException;

public interface AttributesValidator {
    boolean validate(ConsoleAttributes consoleAttributes) throws BadAttributesException;
}
