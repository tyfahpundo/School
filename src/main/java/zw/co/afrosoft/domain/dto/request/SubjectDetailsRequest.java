package zw.co.afrosoft.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubjectDetailsRequest {
    private String name;
    private String code;
}
