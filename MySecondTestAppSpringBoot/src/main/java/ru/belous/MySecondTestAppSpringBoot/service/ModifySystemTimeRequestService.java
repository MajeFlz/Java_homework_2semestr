package ru.belous.MySecondTestAppSpringBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.belous.MySecondTestAppSpringBoot.model.Request;
import ru.belous.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Service
@Qualifier("modifySystemTimeRequestService")
public class ModifySystemTimeRequestService implements ModifyRequestService {

    @Override
    public void modify(Request request) {
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
    }
}