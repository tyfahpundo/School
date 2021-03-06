package zw.co.afrosoft.service;

import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.dto.request.StudentDetailsRequest;
import zw.co.afrosoft.domain.dto.request.StudentUpdateRequest;
import zw.co.afrosoft.domain.dto.response.StudentResponse;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentDetailsRequest studentDetailsRequest);

    List<Student> getAllStudents();

    Student getStudentById(Long id);

    List<Student> searchByName(String name);

    void deleteStudent(Long id);

    List<Student> getPartOneStudents(String name);

    List<Student> searchSophomoreStudents(String name);

    List<Student> searchThirdYearStudents(String name);

    List<Student> searchFinalYearStudents(String name);

    MessageResponse terminateStudent(Long id);

    MessageResponse reinstateStudent(Long id);

    StudentResponse updateStudent(Long id, StudentUpdateRequest studentUpdateRequest);

    Student getStudentByName(String name);
}
