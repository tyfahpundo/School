package zw.co.afrosoft.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.Subject;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {
    private Long id;
    private String name;
    private String code;

    public static SubjectResponse createSubjectResponse(Subject subject) {
        if(Objects.isNull(subject)){
            return null;
        }
        return new SubjectResponse(subject.getSubjectId(), subject.getName(), subject.getCode());
    }
}
