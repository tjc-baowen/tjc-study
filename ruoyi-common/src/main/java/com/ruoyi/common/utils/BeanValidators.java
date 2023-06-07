package com.ruoyi.common.utils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.*;

public class BeanValidators {

    /**
     * 调用JSR303的validate方法, 验证失败时抛出ConstraintViolationException.
     */
    public static void validateWithException(Validator validator,
                                             Object object, Class<?>... groups)
            throws ConstraintViolationException {
        Set constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * 辅助方法,
     * 转换ConstraintViolationException中的Set<ConstraintViolations>中为List<message>.
     */
    public static List<String> extractMessage(ConstraintViolationException e) {
        return extractMessage(e.getConstraintViolations());
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<message>
     */
    public static List<String> extractMessage(
            Set<? extends ConstraintViolation> constraintViolations) {
        List<String> errorMessages = new ArrayList<String>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为Map<property, message>.
     */
    public static Map<String, String> extractPropertyAndMessage(
            Set<? extends ConstraintViolation> constraintViolations) {
        Map<String, String> errorMessages = new HashMap<String, String>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.put(violation.getPropertyPath().toString(),
                    violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<
     * propertyPath message>.
     */
    public static List<String> extractPropertyAndMessageAsList(
            ConstraintViolationException e) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), " ");
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolations>为List<propertyPath message>.
     */
    public static List<String> extractPropertyAndMessageAsList(
            Set<? extends ConstraintViolation> constraintViolations) {
        return extractPropertyAndMessageAsList(constraintViolations, " ");
    }

    /**
     * 辅助方法, 转换ConstraintViolationException中的Set<ConstraintViolations>为List<
     * propertyPath +separator+ message>.
     */
    public static List<String> extractPropertyAndMessageAsList(
            ConstraintViolationException e, String separator) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(),
                separator);
    }

    /**
     * 辅助方法, 转换Set<ConstraintViolation>为List<propertyPath +separator+ message>.
     */
    public static List<String> extractPropertyAndMessageAsList(
            Set<? extends ConstraintViolation> constraintViolations,
            String separator) {
        List<String> errorMessages = new ArrayList<String>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getPropertyPath() + separator
                    + violation.getMessage());
        }
        return errorMessages;
    }
}
