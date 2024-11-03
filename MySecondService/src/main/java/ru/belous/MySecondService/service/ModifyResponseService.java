package ru.belous.MySecondService.service;

import org.springframework.stereotype.Service;
import ru.belous.MySecondService.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}

