package zw.co.afrosoft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
