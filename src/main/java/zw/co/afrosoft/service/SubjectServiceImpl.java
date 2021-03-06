package zw.co.afrosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.Subject;
import zw.co.afrosoft.domain.dto.request.SubjectDetailsRequest;
import zw.co.afrosoft.domain.dto.response.SubjectResponse;
import zw.co.afrosoft.persistence.SubjectRepository;
import zw.co.afrosoft.util.MessageResponse;


import java.util.List;

/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository repo;
    @Override
    public SubjectResponse createSubject(SubjectDetailsRequest subjectDetailsRequest) {
        Subject newSubject = new Subject();
        newSubject.setName(subjectDetailsRequest.getName());
        newSubject.setCode(subjectDetailsRequest.getCode());
        newSubject = repo.save(newSubject);

        SubjectResponse response = new SubjectResponse();
        response.setId(newSubject.getSubjectId());
        response.setName(newSubject.getName());
        response.setCode(newSubject.getCode());
       // response.setEnrolledStudents(newSubject.getEnrolledStudents());

        return response;
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        Subject subject = repo.findById(subjectId).get();
        return subject;
    }

    @Override
    public MessageResponse save(Subject subject) {
        this.repo.save(subject);
        return MessageResponse.createMessageResponse("THE ENROLLMENT WAS SUCCESSFUL");
    }

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> subjects = repo.findAll();
        return subjects;
    }

    @Override
    public MessageResponse saveunenrollment(Subject subject) {
        this.repo.save(subject);
        return MessageResponse.createMessageResponse("THE UN-ENROLLMENT WAS SUCCESSFUL");
    }

    @Override
    public List<Subject> searchByCode(String code) {
        String searchWord = "%".concat(code).concat("%");
        List<Subject> subjectList =  repo.findByCodeLike(searchWord);
        return subjectList;
    }

    @Override
    public MessageResponse deleteSubjectById(Long subjectId) {
        this.repo.deleteById(subjectId);
        return MessageResponse.createMessageResponse("SUBJECT DELETED SUCCESSFULLY!!");
    }

}
