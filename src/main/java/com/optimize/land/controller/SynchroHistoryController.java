package com.optimize.land.controller;


import com.optimize.common.entities.config.CustomMessageSource;
import com.optimize.common.entities.controller.BaseController;
import com.optimize.common.entities.util.Response;
import com.optimize.land.model.dto.SynchroHistoryDto;
import com.optimize.land.model.entity.SynchroHistory;
import com.optimize.land.service.SynchroHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lang-reg/api/v1/synchro-histories")
public class SynchroHistoryController extends BaseController<SynchroHistory, Long> {

    public SynchroHistoryController(CustomMessageSource messageSource,
                                    SynchroHistoryService service) {
        super(messageSource, service);
    }

    @PostMapping(value = "init-synchro")
    public ResponseEntity<Response> initSynchro(@RequestBody @Valid SynchroHistoryDto dto) {
        return new ResponseEntity<>(success(((SynchroHistoryService) service).initSynchro(dto), "init synchro success"), HttpStatus.OK);
    }
}
