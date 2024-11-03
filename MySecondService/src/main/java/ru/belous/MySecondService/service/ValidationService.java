package ru.belous.MySecondService.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.belous.MySecondService.exception.ValidationFailedException;
import ru.belous.MySecondService.exception.UnsupportedCodeException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}