package my.tocker.rxsandbox.service;

import lombok.extern.slf4j.Slf4j;
import my.tocker.rxsandbox.dto.SomeObject;
import my.tocker.rxsandbox.dto.SomeObjectCountWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@Service
@Slf4j
public class CompletableFutureService {

    private Executor threadPoolTaskExecutor;

    @Autowired
    public CompletableFutureService(Executor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    private static final Map<String, SomeObject> DATAMAP;
    static {
        Map<String, SomeObject> initMap = new HashMap<>();

        initMap.put("1", new SomeObject("tocker", 20));
        initMap.put("2", new SomeObject("sucker", 30));

        DATAMAP = Collections.unmodifiableMap(initMap);
    }

    public SomeObjectCountWrapper fetchSomeObjectCountWrapper(String key) {

        CompletableFuture<SomeObject> someObjectCF = findByKeyAsync(key);
        CompletableFuture<Integer> countCF = countAsync(key);

        // join 으로 완료까지 대기 . allof는 completablefuture<void> 리턴.. 각각 결과 추출해야함
        CompletableFuture.allOf(someObjectCF, countCF).join();

        try {
            return SomeObjectCountWrapper.builder()
                    .someObject(someObjectCF.get())
                    .count(countCF.get())
                    .build();
        }
        catch (InterruptedException | ExecutionException ex) {
            return new SomeObjectCountWrapper();
        }
    }

    private CompletableFuture<SomeObject> findByKeyAsync(String key) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("CF1 THREAD {}", Thread.currentThread());
                Thread.sleep(1000);
            } catch (InterruptedException ex) {log.error("error");}
            return DATAMAP.get(key);
        }, threadPoolTaskExecutor);
    }

    private CompletableFuture<Integer> countAsync(String key) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("CF2 THREAD {}", Thread.currentThread());
                Thread.sleep(2000);
            } catch (InterruptedException ex) {log.error("error");}
            return DATAMAP.size();
        }, threadPoolTaskExecutor);
    }

}
