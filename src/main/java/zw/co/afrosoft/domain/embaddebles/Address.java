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
public class Address {
    private String houseNumber;
    private String street;
    private String town;
    private String city;
    private String country;
}
