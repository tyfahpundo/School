package zw.co.afrosoft.controller;

import org.aspectj.bridge.Message;
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
    public ResponseEntity<MessageResponse> enrollStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId){
       Subject subject = service.getSubjectById(subjectId);
       Student student = studentService.getStudentById(studentId);
       student.enrollStudent(subject);
       MessageResponse messageResponse = service.save(subject);
       return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
    @PutMapping("/{subjectId}/subject/{studentId}")
    public ResponseEntity<MessageResponse> unenrollStudent(@PathVariable Long subjectId,@PathVariable Long studentId){
        Subject subject = service.getSubjectById(subjectId);
        Student student = studentService.getStudentById(studentId);
        student.unenrollStudent(subject);
        MessageResponse messageResponse = service.saveunenrollment(subject);
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
    @GetMapping("/get-all-subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(){
        List<Subject> response = service.getAllSubjects();
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    @GetMapping("/search-by-subject-code")
    public ResponseEntity<List<Subject>> searchByCode(@RequestParam String code){
        List<Subject> subjectList = service.searchByCode(code);
        return new ResponseEntity<>(subjectList,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<MessageResponse> deleteSubject(@PathVariable Long subjectId){
        MessageResponse messageResponse = service.deleteSubjectById(subjectId);
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
}
