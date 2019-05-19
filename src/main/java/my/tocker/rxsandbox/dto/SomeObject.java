package my.tocker.rxsandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SomeObject {

    private String name;
    private int age;

}
