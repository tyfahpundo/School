package zw.co.afrosoft.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.TeacherLevel;
import zw.co.afrosoft.domain.enums.TeacherStatus;

import javax.persistence.*;

/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name",nullable = false)
    private String name;
    @Column(name = "last_name",nullable = false)
    private String surname;
    @Column(name = "age",nullable = false)
    private int age;
    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    private TeacherLevel level;
    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TeacherStatus status;
    @Embedded
    private Address address;
    @Embedded
    private ContactDetail contactDetails;

//    private Set<Subject> subjects;
}
