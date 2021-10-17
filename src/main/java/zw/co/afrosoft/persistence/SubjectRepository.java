package zw.co.afrosoft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
}
