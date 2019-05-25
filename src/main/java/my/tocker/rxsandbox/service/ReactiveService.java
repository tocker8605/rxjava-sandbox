package my.tocker.rxsandbox.service;

import lombok.extern.slf4j.Slf4j;
import my.tocker.rxsandbox.dto.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executor;
import rx.Observable;

@Service
@Slf4j
public class ReactiveService {

    private Executor threadPoolTaskExecutor;

    @Autowired
    public ReactiveService(Executor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    private static final Map<String, SomeObject> DATAMAP;
    static {
        Map<String, SomeObject> initMap = new HashMap<>();

        initMap.put("1", new SomeObject("tocker", 20));
        initMap.put("2", new SomeObject("sucker", 30));

        DATAMAP = Collections.unmodifiableMap(initMap);
    }

    public List<SomeObject> fetchSomeObject() {
        return new ArrayList<>(DATAMAP.values());
    }

    public List<SomeObject> fetchSomeObject_1() {
        Observable<SomeObject> obs = Observable.merge(observable1(), observable2());
        List<SomeObject> soList = new ArrayList<>();

        // !! 안기다림.. 당연한거지
        obs.subscribe(so -> soList.add(so));

        return soList;
    }

    private Observable<SomeObject> observable1() {
        return Observable.create(s -> {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    log.info("OB1 THREAD {}", Thread.currentThread());
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {log.error("error");}
                s.onNext(DATAMAP.get("1"));
                s.onNext(DATAMAP.get("1"));
                s.onNext(DATAMAP.get("1"));
                s.onCompleted();
            });
        });
    }

    private Observable<SomeObject> observable2() {
        return Observable.create(s -> {
            threadPoolTaskExecutor.execute(() -> {
                try {
                    log.info("OB2 THREAD {}", Thread.currentThread());
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {log.error("error");}
                s.onNext(DATAMAP.get("2"));
                s.onNext(DATAMAP.get("2"));
                s.onNext(DATAMAP.get("2"));
                s.onCompleted();
            });
        });
    }

}
