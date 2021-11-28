package zw.co.afrosoft.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import zw.co.afrosoft.domain.Subject;
import zw.co.afrosoft.domain.Teacher;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.TeacherLevel;
import zw.co.afrosoft.domain.enums.TeacherStatus;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class TeacherResponse {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private TeacherLevel teacherLevel;
    private TeacherStatus teacherStatus;
    private ContactDetail contactDetail;
    private Address address;

    public TeacherResponse() {

    }

    public static TeacherResponse createTeacherResponse(Teacher teacher){
        if(Objects.isNull(teacher)){
            return null;
        }
        return new TeacherResponse(teacher.getId(), teacher.getName(), teacher.getSurname(), teacher.getAge(),
                teacher.getLevel(),teacher.getStatus(), teacher.getContactDetails(),
                teacher.getAddress());
    }
}
