package my.tocker.rxsandbox.controller;

import my.tocker.rxsandbox.dto.SomeObject;
import my.tocker.rxsandbox.service.ReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reactive")
public class SomeController {

    private ReactiveService reactiveService;

    @Autowired
    public SomeController(ReactiveService reactiveService) {
        this.reactiveService = reactiveService;
    }

    @GetMapping
    public List<SomeObject> responseReactive() {
        return reactiveService.fetchSomeObject_1();
    }

}
