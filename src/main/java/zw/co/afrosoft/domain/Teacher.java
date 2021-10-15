package zw.co.afrosoft.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
