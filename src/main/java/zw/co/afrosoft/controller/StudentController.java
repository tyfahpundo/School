package zw.co.afrosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.dto.request.StudentDetailsRequest;
import zw.co.afrosoft.domain.dto.request.StudentUpdateRequest;
import zw.co.afrosoft.domain.dto.response.StudentResponse;
import zw.co.afrosoft.service.StudentService;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService service;
    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentDetailsRequest studentDetailsRequest){
        StudentResponse student = service.createStudent(studentDetailsRequest);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> studentList = service.getAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

    @GetMapping("/get-student/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        Student student = service.getStudentById(id);
        return new ResponseEntity<>(student,HttpStatus.FOUND);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchByName(@RequestParam String name){
        List<Student> students = service.searchByName(name);
        return new ResponseEntity<>(students,HttpStatus.FOUND);
    }
    @GetMapping("/search-part1-students")
    public ResponseEntity<List<Student>> getPartOneStudents(@RequestParam String name){
        List<Student> studentList = service.getPartOneStudents(name);
        return new ResponseEntity<>(studentList,HttpStatus.FOUND);
    }
    @GetMapping("/search-sophomore-students")
    public ResponseEntity<List<Student>> searchSophomoreStudents(@RequestParam String name){
        List<Student> studentList = service.searchSophomoreStudents(name);
        return new ResponseEntity<>(studentList,HttpStatus.FOUND);
    }
    @GetMapping("/search-third-year-students")
    public ResponseEntity<List<Student>> searchThirdYearStudents(@RequestParam String name){
        List<Student> studentList = service.searchThirdYearStudents(name);
        return new ResponseEntity<>(studentList,HttpStatus.FOUND);
    }
    @GetMapping("/search-final-year-students")
    public ResponseEntity<List<Student>> searchFinalYearStudents(@RequestParam String name){
        List<Student> studentList = service.searchFinalYearStudents(name);
        return new ResponseEntity<>(studentList,HttpStatus.FOUND);
    }
    @PutMapping("/terminate/{id}")
    public ResponseEntity<MessageResponse> terminateStudent(@PathVariable Long id){
        MessageResponse messageResponse = service.terminateStudent(id);
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
    @PutMapping("/reinstate/{id}")
    public ResponseEntity<MessageResponse> reinstateStudent(@PathVariable Long id){
        MessageResponse messageResponse = service.reinstateStudent(id);
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id,@RequestBody StudentUpdateRequest studentUpdateRequest){
        StudentResponse student = service.updateStudent(id,studentUpdateRequest);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
