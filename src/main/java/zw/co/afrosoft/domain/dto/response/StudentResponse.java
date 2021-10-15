package zw.co.afrosoft.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.enums.StudentStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private StudentLevel studentLevel;
    private StudentStatus studentStatus;
    private ContactDetail contactDetail;
    private Address address;
}
