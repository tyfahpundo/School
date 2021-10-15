package zw.co.afrosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.dto.request.StudentDetailsRequest;
import zw.co.afrosoft.domain.dto.response.StudentResponse;
import zw.co.afrosoft.persistence.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository repo;
    @Override
    public StudentResponse createStudent(StudentDetailsRequest studentDetailsRequest) {
        //Creating a Student Object
        Student newStudent = new Student();
        //Mapping new student details from the request
        newStudent.setName(studentDetailsRequest.getName());
        newStudent.setSurname(studentDetailsRequest.getSurname());
        newStudent.setAge(studentDetailsRequest.getAge());
        newStudent.setStudentLevel(studentDetailsRequest.getStudentLevel());
        newStudent.setStudentStatus(studentDetailsRequest.getStudentStatus());
        newStudent.setContactDetail(studentDetailsRequest.getContactDetail());
        newStudent.setAddress(studentDetailsRequest.getAddress());
        newStudent=repo.save(newStudent);

        //Mapping student details to the response
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(newStudent.getId());
        studentResponse.setName(newStudent.getName());
        studentResponse.setSurname(newStudent.getSurname());
        studentResponse.setAge(newStudent.getAge());
        studentResponse.setStudentLevel(newStudent.getStudentLevel());
        studentResponse.setStudentStatus(newStudent.getStudentStatus());
        studentResponse.setContactDetail(newStudent.getContactDetail());
        studentResponse.setAddress(newStudent.getAddress());

        return studentResponse;
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public List<Student> searchByName(String name) {
        String searchWord = "%".concat(name).concat("%");
        List<Student> students =repo.findByNameLike(searchWord);

        return students;
    }

    @Override
    public void deleteStudent(Long id) {
        if(repo.getById(id).getId().equals(id)){
            repo.deleteById(id);
        }else{
            System.out.println("No Record Found");
        }
    }

    @Override
    public List<Student> getPartOneStudents(String name) {
        String searchWord = "%".concat(name).concat("%");
        return repo
                .findByNameLike(searchWord)
                .stream()
                .filter(s -> s.getStudentLevel() == StudentLevel.FIRST_YEAR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> searchSophomoreStudents(String name) {
        String searchWord = "%".concat(name).concat("%");
        return repo.findByNameLike(searchWord)
                .stream()
                .filter(s -> s.getStudentLevel() == StudentLevel.SOPHOMORE)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> searchThirdYearStudents(String name) {
        String searchWord = "%".concat(name).concat("%");
        return repo.findByNameLike(searchWord)
                .stream()
                .filter(s -> s.getStudentLevel() == StudentLevel.THIRD_YEAR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> searchFinalYearStudents(String name) {
        String searchWord = "%".concat(name).concat("%");
        return repo
                .findByNameLike(searchWord)
                .stream()
                .filter(s -> s.getStudentLevel() == StudentLevel.FINAL_YEAR)
                .collect(Collectors.toList());
    }

}
