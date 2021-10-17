package zw.co.afrosoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "subject_name",nullable = false)
    private String name;
    @Column(name = "subject_code",nullable = false)
    private String code;
    @ManyToMany
    @Column(name = "students")
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "subject id"),
            inverseJoinColumns = @JoinColumn(name ="student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<>();

    public void enrollStudent(Student student){
        enrolledStudents.add(student);
    }
}
