package ru.belous.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.belous.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.belous.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.belous.MySecondTestAppSpringBoot.model.Request;
import ru.belous.MySecondTestAppSpringBoot.model.Response;
import ru.belous.MySecondTestAppSpringBoot.service.ValidationService;


@RestController
public class MyController {

    private final ValidationService validationService;

    @Autowired
    public MyController(ValidationService validationService) {

        this.validationService = validationService;

    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody @Valid Request request, BindingResult bindingResult) {
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            if ("123".equals(request.getUid())) {
                throw new UnsupportedCodeException();
            }


            if (bindingResult.hasErrors()) {
                StringBuilder errorMessage = new StringBuilder("Ошибки валидации: ");
                bindingResult.getFieldErrors().forEach(error -> {
                    errorMessage.append(String.format("[%s: %s] ", error.getField(), error.getDefaultMessage()));
                });
                throw new ValidationFailedException(errorMessage.toString());
            }

        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("UID равен 123, что не поддерживается");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}