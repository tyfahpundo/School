package zw.co.afrosoft.domain.dto.request;

import lombok.*;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.enums.StudentStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUpdateRequest {
    private String name;
    private String surname;
    private int age;
    private ContactDetail contactDetail;
    private Address address;


}
