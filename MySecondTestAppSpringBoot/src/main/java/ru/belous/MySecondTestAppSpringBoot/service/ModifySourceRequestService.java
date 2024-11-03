package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.belous.MySecondTestAppSpringBoot.model.Request;

@Service
@Qualifier("modifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSource("it's service-1");
    }
}