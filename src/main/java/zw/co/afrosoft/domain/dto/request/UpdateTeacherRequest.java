package zw.co.afrosoft.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.TeacherLevel;
import zw.co.afrosoft.domain.enums.TeacherStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherRequest {
    private String name;
    private String surname;
    private int age;
    private TeacherLevel teacherLevel;
    private TeacherStatus teacherStatus;
    private ContactDetail contactDetail;
    private Address address;
}
