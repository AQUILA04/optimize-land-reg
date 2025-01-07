package com.optimize.land.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.optimize.common.entities.config.CustomMessageSource;
import com.optimize.common.entities.controller.BaseController;
import com.optimize.common.entities.util.Response;
import com.optimize.land.model.dto.ActorDto;
import com.optimize.land.model.dto.BioAuthDto;
import com.optimize.land.model.entity.AbstractActor;
import com.optimize.land.model.enumeration.RegistrationStatus;
import com.optimize.land.service.ActorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lang-reg/api/v1/actors")
public class ActorController extends BaseController<AbstractActor, Long> {

    public ActorController(CustomMessageSource messageSource, ActorService service) {
        super(messageSource, service);
    }

    @PostMapping
    public ResponseEntity<Response> register(@RequestBody @Valid ActorDto actorDto) throws JsonProcessingException {
        return new ResponseEntity<>(success(getService().register(actorDto), "Actor register successfully"), HttpStatus.CREATED);
    }

    @GetMapping
    @Override
    public ResponseEntity<Response> getAll(Pageable pageable) {
        return super.getAll(pageable);
    }

    @GetMapping(value = "all")
    @Override
    public ResponseEntity<Response> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "by-status")
    public ResponseEntity<Response> getAllByStatus(RegistrationStatus status, Pageable pageable) {
        return new ResponseEntity<>(success(getService().getByStatus(status, pageable), "Successful get all actor by status"), HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    @Override
    public ResponseEntity<Response> getOne(@PathVariable Long id) {
        return super.getOne(id);
    }

    @DeleteMapping(value = "{id}")
    @Override
    public ResponseEntity<Response> deleteSoft(@PathVariable Long id) {
        return super.deleteSoft(id);
    }

    @PostMapping(value = "bio-auth")
    public ResponseEntity<Response> bioAuthentication(@RequestBody @Valid BioAuthDto dto) {
        return new ResponseEntity<>(success(getService().bioAuth(dto), "success bio authentication"), HttpStatus.OK);
    }

    public ActorService getService() {
        return (ActorService) service;
    }
}
