package my.tocker.rxsandbox.service;

import my.tocker.rxsandbox.dto.SomeObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReactiveService {

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

}
