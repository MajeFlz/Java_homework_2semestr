package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.belous.MySecondTestAppSpringBoot.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}

