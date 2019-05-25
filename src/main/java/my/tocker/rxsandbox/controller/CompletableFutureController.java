package my.tocker.rxsandbox.controller;

import my.tocker.rxsandbox.dto.SomeObjectCountWrapper;
import my.tocker.rxsandbox.service.CompletableFutureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cf")
public class CompletableFutureController {

    private CompletableFutureService completableFutureService;

    @Autowired
    public CompletableFutureController(CompletableFutureService completableFutureService) {
        this.completableFutureService = completableFutureService;
    }

    @GetMapping
    public SomeObjectCountWrapper response(@RequestParam("key") String key) {
        return completableFutureService.fetchSomeObjectCountWrapper(key);
    }

}
