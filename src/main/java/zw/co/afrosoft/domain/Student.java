package zw.co.afrosoft.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import zw.co.afrosoft.domain.embaddebles.Address;
import zw.co.afrosoft.domain.embaddebles.ContactDetail;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.enums.StudentStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentId;
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
    @ManyToMany
    private Set<Subject> subjects = new HashSet<>();

    public void enrollStudent(Subject subject){
        subjects.add(subject);
    }
    public void unenrollStudent(Subject subject){
        subjects.remove(subject);
    }
}
