package com.nayker.chat.validator;


import com.nayker.chat.config.ValidatorConfig;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PasswordConstrainValidator implements ConstraintValidator<PasswordConstrain, String> {

    private final PasswordValidator validator;

    public PasswordConstrainValidator(ValidatorConfig config) {
        this.validator = config.PasswordValidator();
    }

    public void initialize(PasswordConstrain constrain) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join("  ", messages);
        context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation().disableDefaultConstraintViolation();
        return false;
    }
}
