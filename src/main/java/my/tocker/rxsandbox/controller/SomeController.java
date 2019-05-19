package my.tocker.rxsandbox.controller;

import my.tocker.rxsandbox.dto.SomeObject;
import my.tocker.rxsandbox.service.ReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class SomeController {

    ReactiveService reactiveService;

    @Autowired
    public SomeController(ReactiveService reactiveService) {
        this.reactiveService = reactiveService;
    }

    @GetMapping
    public SomeObject response() {
        return reactiveService.fetchSomeObject();
    }

}
