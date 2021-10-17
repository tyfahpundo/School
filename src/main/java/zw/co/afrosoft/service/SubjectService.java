package zw.co.afrosoft.service;

import zw.co.afrosoft.domain.Subject;
import zw.co.afrosoft.domain.dto.request.SubjectDetailsRequest;
import zw.co.afrosoft.domain.dto.response.SubjectResponse;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    SubjectResponse createSubject(SubjectDetailsRequest subjectDetailsRequest);

    Subject getSubjectById(Long subjectId);

    MessageResponse save(Subject subject);

    List<Subject> getAllSubjects();
}
