package zw.co.afrosoft.domain.embaddebles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetail {
    private String phone;
    private String email;
    private String nationalIdNumber;
}
