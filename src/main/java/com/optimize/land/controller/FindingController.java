package com.optimize.land.controller;

import com.optimize.common.entities.config.CustomMessageSource;
import com.optimize.common.entities.controller.BaseController;
import com.optimize.land.model.entity.Finding;
import com.optimize.land.service.FindingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lang-reg/api/v1/constatations")
public class FindingController extends BaseController<Finding, Long> {
    public FindingController(CustomMessageSource messageSource,
                             FindingService service) {
        super(messageSource, service);
    }
}
