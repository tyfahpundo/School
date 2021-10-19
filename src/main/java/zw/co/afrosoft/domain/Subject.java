package zw.co.afrosoft.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subjectId;
    @Column(name = "subject_name",nullable = false)
    private String name;
    @Column(name = "subject_code",nullable = false)
    private String code;

}
