package com.aap.springboot_skeleton.model.validator;


import ch.qos.logback.classic.gaffer.PropertyUtil;
import org.springframework.beans.PropertyAccessorUtils;
import org.yaml.snakeyaml.introspector.PropertyUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Arrays;
import java.util.List;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ValuesAllowed.Validator.class })
public @interface ValuesAllowed {

    String message() default "Field value should be from list of ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values();

    class Validator implements ConstraintValidator<ValuesAllowed, Object> {
        private String message;
        private List<Object> allowable;

        @Override
        public void initialize(ValuesAllowed requiredIfChecked) {
            this.message = requiredIfChecked.message();
            this.allowable = Arrays.asList(requiredIfChecked.values());
        }

        public boolean isValid(Object value, ConstraintValidatorContext context) {
            boolean valid = value == null || this.allowable.contains(value);

            if (!valid) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(message.concat(this.allowable.toString()))
                        .addPropertyNode(PropertyAccessorUtils.getPropertyName(value.toString())).addConstraintViolation();
            }
            return valid;
        }
    }
}

