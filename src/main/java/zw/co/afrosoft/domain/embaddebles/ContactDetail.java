package zw.co.afrosoft.domain.embaddebles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactDetail implements Serializable {
    private String phone;
    private String email;
    private String nationalIdNumber;
}
