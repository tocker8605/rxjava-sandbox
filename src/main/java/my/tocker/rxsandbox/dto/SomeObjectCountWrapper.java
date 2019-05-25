package my.tocker.rxsandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SomeObjectCountWrapper {

    private SomeObject someObject;
    private int count;

}
