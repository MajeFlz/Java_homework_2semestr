package ru.belous.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.belous.MySecondTestAppSpringBoot.exception.*;
import ru.belous.MySecondTestAppSpringBoot.model.*;
import ru.belous.MySecondTestAppSpringBoot.service.*;
import ru.belous.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;
    private final QuarterlyBonusService quarterlyBonusService;
    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("modifySystemTimeRequestService") ModifyRequestService modifyRequestService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        AnnualBonusService annualBonusService,
                        QuarterlyBonusService quarterlyBonusService) {

        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody @Valid Request request, BindingResult bindingResult) {

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("response created: {}", response);

        try {
            if ("123".equals(request.getUid())) {
                log.error("uid is 123, throw UnsupportedException");
                throw new UnsupportedCodeException();
            }

            validationService.isValid(bindingResult);

        } catch (ValidationFailedException e) {
            return generateErrorResponse(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            return generateErrorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return generateErrorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setAnnualBonus(annualBonusService.calculate(request.getPosition(), request.getSalary(),
                request.getBonus(), request.getWorkDays(), Year.now().getValue()));

        if (request.isManager()) {
            response.setQuarterlyBonus(quarterlyBonusService.calculate(true, request.getPosition(),
                    request.getSalary(), request.getBonus(), request.getWorkDays(), Year.now().getValue(), getCurrentQuarter()));
        }
        modifyRequestService.modify(request);
        return ResponseEntity.ok(modifyResponseService.modify(response));
    }
    private ResponseEntity<Response> generateErrorResponse(Response response, ErrorCodes code, ErrorMessages message,
                                                           HttpStatus status) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(code);
        response.setErrorMessage(message);
        log.info("response error: {}", response);
        return new ResponseEntity<>(response, status);
    }

    private int getCurrentQuarter() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int monthValue = calendar.get(Calendar.MONTH);
        return monthValue / 3;
    }
}