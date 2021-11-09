package zw.co.afrosoft.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.afrosoft.domain.Subject;
import zw.co.afrosoft.domain.Teacher;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {
    private Long id;
    private String name;
    private String code;
    private Teacher teacher;

    public static SubjectResponse createSubjectResponse(Subject subject) {
        if(Objects.isNull(subject)){
            return null;
        }
        return new SubjectResponse(subject.getSubjectId(), subject.getName(), subject.getCode(), subject.getTeacher());
    }
}
