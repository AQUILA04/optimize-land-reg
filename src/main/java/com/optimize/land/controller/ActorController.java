package com.optimize.land.controller;

import com.optimize.common.entities.config.CustomMessageSource;
import com.optimize.common.entities.controller.BaseController;
import com.optimize.common.entities.util.Response;
import com.optimize.land.model.dto.ActorDto;
import com.optimize.land.model.entity.AbstractActor;
import com.optimize.land.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lang-reg/api/v1/actors")
public class ActorController extends BaseController<AbstractActor, Long> {

    public ActorController(CustomMessageSource messageSource, ActorService service) {
        super(messageSource, service);
    }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody @Valid ActorDto actorDto) {
        return new ResponseEntity<>(success(getService().register(actorDto), "Actor register successfully"), HttpStatus.CREATED);
    }

    public ActorService getService() {
        return (ActorService) service;
    }
}
