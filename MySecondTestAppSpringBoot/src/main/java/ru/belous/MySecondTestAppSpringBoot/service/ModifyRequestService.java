package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.belous.MySecondTestAppSpringBoot.model.Request;
@Service
public interface ModifyRequestService {

    void modify(Request request);
}
