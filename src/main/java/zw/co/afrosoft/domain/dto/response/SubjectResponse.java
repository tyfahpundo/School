package zw.co.afrosoft.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.afrosoft.domain.Student;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {
    private Long id;
    private String name;
    private String code;
    private Set<Student> enrolledStudents;

}
