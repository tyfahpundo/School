package zw.co.afrosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrosoft.domain.Teacher;
import zw.co.afrosoft.domain.dto.request.TeacherDetailsRequest;
import zw.co.afrosoft.domain.dto.request.UpdateTeacherRequest;
import zw.co.afrosoft.domain.dto.response.TeacherResponse;
import zw.co.afrosoft.service.TeacherService;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping("/create")
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody TeacherDetailsRequest teacherDetailsRequest){
        TeacherResponse teacher = teacherService.createTeacher(teacherDetailsRequest);
        return new ResponseEntity<>(teacher,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id){
        Teacher teacher = teacherService.getTeacherById(id);
        return new ResponseEntity<>( TeacherResponse.createTeacherResponse(teacher), HttpStatus.OK);
    }
    @GetMapping("/get-all-teachers")
    public ResponseEntity<List<TeacherResponse>> getAllTeachers(){
        return new ResponseEntity<>(teacherService.getAllTeachers().stream()
                .map(TeacherResponse::createTeacherResponse)
                .collect(Collectors.toList())
                ,HttpStatus.OK);
    }
    @GetMapping("/all-active-teachers")
    public ResponseEntity<List<TeacherResponse>> getAllActiveTeachers(){
        return new ResponseEntity<>(teacherService.getAllActiveTeachers().stream()
                .map(TeacherResponse::createTeacherResponse)
                .collect(Collectors.toList())
                ,HttpStatus.FOUND);
    }
    @GetMapping("/all-inactive-teachers")
    public ResponseEntity<List<TeacherResponse>> getAllInactiveTeachers(){
        return new ResponseEntity<>(teacherService.getAllInactiveTeachers()
                .stream()
                .map(TeacherResponse::createTeacherResponse)
                .collect(Collectors.toList())
                ,HttpStatus.FOUND);
    }
    @PutMapping("/deactivate-teacher/{id}")
    public ResponseEntity<MessageResponse> deactivateTeacher(@PathVariable Long id){
        return new ResponseEntity<>(teacherService.deactivateTeacher(id),HttpStatus.OK);
    }
    @PutMapping("/activate-teacher/{id}")
    public ResponseEntity<MessageResponse> activateTeacher(@PathVariable Long id){
        return new ResponseEntity<>(teacherService.activateTeacher(id),HttpStatus.OK);
    }
    @PutMapping("/update-teacher/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(@PathVariable Long id, @RequestBody UpdateTeacherRequest updateTeacherRequest){
        TeacherResponse updatedTeacher = teacherService.updateTeacher(id,updateTeacherRequest);
        return new ResponseEntity<>(updatedTeacher,HttpStatus.OK);
    }
}
