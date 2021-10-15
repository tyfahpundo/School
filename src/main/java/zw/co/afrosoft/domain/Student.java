package zw.co.afrosoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.enums.StudentStatus;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name",nullable = false)
    private String name;
    @Column(name = "last_name",nullable = false)
    private String surname;
    @Column(name = "age",nullable = false)
    private int age;
    @Column(name= "level",nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentLevel studentLevel;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;
    @Embedded
    private ContactDetail contactDetail;
    @Embedded
    private Address address;
}
