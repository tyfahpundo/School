package zw.co.afrosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.Subject;
import zw.co.afrosoft.domain.dto.request.SubjectDetailsRequest;
import zw.co.afrosoft.domain.dto.response.SubjectResponse;
import zw.co.afrosoft.service.StudentService;
import zw.co.afrosoft.service.SubjectService;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService service;
    @PostMapping("/create")
    public ResponseEntity<SubjectResponse> createSubject(@RequestBody SubjectDetailsRequest subjectDetailsRequest){
        SubjectResponse response = service.createSubject(subjectDetailsRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{subjectId}/student/{studentId}")
    public ResponseEntity<MessageResponse> enrollStudentToSubject(@PathVariable(name = "id") Long subjectId, @PathVariable(name = "id") Long studentId){
       Subject subject = service.getSubjectById(subjectId);
       Student student = studentService.getStudentById(studentId);
       subject.enrollStudent(student);
       MessageResponse messageResponse = service.save(subject);
       return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
    @GetMapping("/get-all-subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> response = service.getAllSubjects();
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
}
