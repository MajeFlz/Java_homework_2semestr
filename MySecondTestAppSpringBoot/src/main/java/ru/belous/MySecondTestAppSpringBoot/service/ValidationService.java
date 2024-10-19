package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.belous.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.belous.MySecondTestAppSpringBoot.exception.ValidationFailedException;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException;
}