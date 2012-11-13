package net.cyberward.springjersey.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

import net.cyberward.springjersey.repository.AbstractBaseDTO;


/**
 * An exception to hold the Set of ContstraintViolations and translate to a
 * message.
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -8362895298532098190L;

    public <T extends AbstractBaseDTO> ValidationException(Set<ConstraintViolation<T>> violations) {
	super(convertToMessage(violations));
    }

    //This is not a good translation. Needs work.
    private static <T extends AbstractBaseDTO> String convertToMessage(
	    Set<ConstraintViolation<T>> violations) {
	StringBuilder builder = new StringBuilder();
	for (ConstraintViolation<T> constraintViolation : violations) {
	    builder.append(constraintViolation.getMessage());
	    builder.append("\n");
	}
	return builder.toString();
    }
}
