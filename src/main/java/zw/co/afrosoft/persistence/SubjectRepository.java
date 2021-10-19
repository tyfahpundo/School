package zw.co.afrosoft.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.domain.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    List<Subject> findByCodeLike(String searchWord);
}
