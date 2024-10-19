package ru.belous.MySecondTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.belous.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.belous.MySecondTestAppSpringBoot.exception.ValidationFailedException;
@Slf4j
@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException{
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Ошибки валидации: ");
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()));
            });

            log.error("bindingResult has errors, throw ValidationFailedException");
            throw new ValidationFailedException(errorMessage.toString());
        }
    }
}
