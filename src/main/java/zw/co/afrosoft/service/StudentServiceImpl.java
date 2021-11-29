package zw.co.afrosoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.dto.request.StudentUpdateRequest;
import zw.co.afrosoft.domain.enums.StudentLevel;
import zw.co.afrosoft.domain.Student;
import zw.co.afrosoft.domain.dto.request.StudentDetailsRequest;
import zw.co.afrosoft.domain.dto.response.StudentResponse;
import zw.co.afrosoft.domain.enums.StudentStatus;
import zw.co.afrosoft.exceptions.BusinessException;
import zw.co.afrosoft.persistence.StudentRepository;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository repo;
    @Override
    public StudentResponse createStudent(StudentDetailsRequest studentDetailsRequest) {
        if(studentDetailsRequest.getName().isEmpty()|| studentDetailsRequest.getName().length()==0){
            throw new BusinessException("The Name should not be Blank");
        }
        try {
            //Creating a Student Object
            Student newStudent = new Student().builder()
                    .name(studentDetailsRequest.getName())
                    .surname(studentDetailsRequest.getSurname())
                    .age(studentDetailsRequest.getAge())
                    .studentLevel(studentDetailsRequest.getStudentLevel())
                    .studentStatus(studentDetailsRequest.getStudentStatus())
                    .contactDetail(studentDetailsRequest.getContactDetail())
                    .address(studentDetailsRequest.getAddress())
                    .build();
            //Mapping new student details from the request
            newStudent = repo.save(newStudent);

            //Mapping student details to the response
            StudentResponse studentResponse = new StudentResponse().builder()
                    .id(newStudent.getStudentId())
                    .name(newStudent.getName())
                    .surname(newStudent.getSurname())
                    .age(newStudent.getAge())
                    .studentLevel(newStudent.getStudentLevel())
                    .studentStatus(newStudent.getStudentStatus())
                    .contactDetail(newStudent.getContactDetail())
                    .address(newStudent.getAddress())
                    .build();
            return studentResponse;
        }catch (IllegalArgumentException e){
            throw new BusinessException("Something went wrong in the Service Layer while adding the Student "+e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            List<Student> savedStudents = repo.findAll();
            if(savedStudents.isEmpty()){
                throw new BusinessException("The List of Students is Currently Empty, We have nothing to Display");
            }
            return savedStudents;
        }catch (Exception e){
            throw new BusinessException("Something went wrong in the Service Layer While fetching all students "+ e.getMessage());
        }

    }

    @Override
    public Student getStudentById(Long id) {
        try{
        Student student = repo.findById(id).get();
        return student;

        }catch (IllegalArgumentException e){
            throw new BusinessException("The Id provided is Null, Please povide a valid id "+e.getMessage());
        }catch(NoSuchElementException e){
            throw  new BusinessException("There is no Element with the given Id"+e.getMessage());
        }
    }

    @Override
    public List<Student> searchByName(String name) {
        try {
            String searchWord = "%".concat(name).concat("%");
            List<Student> students = repo.findByNameLike(searchWord);

            return students;
        }catch (IllegalArgumentException e){
            throw new BusinessException("The Name provided is null, Please provide an Name "+e.getMessage());
        }catch (NoSuchElementException e){
            throw new BusinessException("There is no Element with the given name "+e.getMessage());
        }
    }

    @Override
    public void deleteStudent(Long id) {
        if(repo.getById(id).getStudentId().equals(id)){
            repo.deleteById(id);
        }else{
            System.out.println("No Record Found");
        }
    }

    @Override
    public List<Student> getPartOneStudents(String name) {
        try {
            String searchWord = "%".concat(name).concat("%");
            return repo
                    .findByNameLike(searchWord)
                    .stream()
                    .filter(s -> s.getStudentLevel() == StudentLevel.FIRST_YEAR)
                    .collect(Collectors.toList());
        }catch(IllegalArgumentException e){
            throw new BusinessException("The Provided name is Null, Please provide a name "+e.getMessage());
        }catch(NoSuchElementException e){
            throw new BusinessException("There is No Part One Student with the provided name"+e.getMessage());
        }
    }

    @Override
    public List<Student> searchSophomoreStudents(String name) {
        try {
            String searchWord = "%".concat(name).concat("%");
            return repo.findByNameLike(searchWord)
                    .stream()
                    .filter(s -> s.getStudentLevel() == StudentLevel.SOPHOMORE)
                    .collect(Collectors.toList());
        }catch(IllegalArgumentException e){
            throw new BusinessException("The Provided name is Null, Please provide a name "+e.getMessage());
        }catch(NoSuchElementException e){
            throw new BusinessException("There is No Sophomore Student with the provided name"+e.getMessage());
        }
    }

    @Override
    public List<Student> searchThirdYearStudents(String name) {
        try {
            String searchWord = "%".concat(name).concat("%");
            return repo.findByNameLike(searchWord)
                    .stream()
                    .filter(s -> s.getStudentLevel() == StudentLevel.THIRD_YEAR)
                    .collect(Collectors.toList());
        }catch(IllegalArgumentException e){
        throw new BusinessException("The Provided name is Null, Please provide a name "+e.getMessage());
    }catch(NoSuchElementException e){
        throw new BusinessException("There is No Third Year Student with the provided name"+e.getMessage());
    }
    }

    @Override
    public List<Student> searchFinalYearStudents(String name) {
        try {
            String searchWord = "%".concat(name).concat("%");
            return repo
                    .findByNameLike(searchWord)
                    .stream()
                    .filter(s -> s.getStudentLevel() == StudentLevel.FINAL_YEAR)
                    .collect(Collectors.toList());
        }catch(IllegalArgumentException e){
        throw new BusinessException("The Provided name is Null, Please provide a name "+e.getMessage());
    }catch(NoSuchElementException e){
        throw new BusinessException("There is No Part Final Student with the provided name"+e.getMessage());
    }
    }

    @Override
    public MessageResponse terminateStudent(Long id) {
        try {
            Student student = getStudentById(id);
            if(student.getStudentStatus()== StudentStatus.ACTIVE){
                student.setStudentStatus(StudentStatus.INACTIVE);
                this.repo.save(student);
                return MessageResponse.createMessageResponse("STUDENT SUCCESSFULLY TERMINATED");
            }else{
                return MessageResponse.createMessageResponse("STUDENT ALREADY TERMINATED");
            }

        }catch (Exception e){
            throw new BusinessException("Something went wrong in the Service Layer while terminating the Student");
        }

    }

    @Override
    public MessageResponse reinstateStudent(Long id) {
        try {
            Student student = repo.findById(id).get();
            if(student.getStudentStatus() == StudentStatus.INACTIVE){
                student.setStudentStatus(StudentStatus.ACTIVE);
                this.repo.save(student);
                return MessageResponse.createMessageResponse("STUDENT SUCCESSFULLY RE_INSTATED");
            }else{
                return MessageResponse.createMessageResponse("STUDENT IS ALREADY ACTIVE!!!!");
            }

        }catch(Exception e){
            throw new BusinessException("Something went wrong in the Service Layer while reinstating the student");
        }
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateRequest studentUpdateRequest) {
        try {
            Student updateStudent = repo.findById(id).get();
            updateStudent.builder()
                    .name(studentUpdateRequest.getName())
                    .surname(studentUpdateRequest.getSurname())
                    .age(studentUpdateRequest.getAge())
                    .contactDetail(studentUpdateRequest.getContactDetail())
                    .address(studentUpdateRequest.getAddress())
                    .build();
            updateStudent = repo.save(updateStudent);

            StudentResponse studentResponse = new StudentResponse().builder()
                    .id(updateStudent.getStudentId())
                    .name(updateStudent.getName())
                    .surname(updateStudent.getSurname())
                    .age(updateStudent.getAge())
                    .studentLevel(updateStudent.getStudentLevel())
                    .studentStatus(updateStudent.getStudentStatus())
                    .contactDetail(updateStudent.getContactDetail())
                    .address(updateStudent.getAddress())
                    .build();
            return studentResponse;
        }catch (Exception e){
            throw new BusinessException("Something went wrong in the Service Layer while updating the Student");
        }
    }

    @Override
    public Student getStudentByName(String name) {
        try{
            Student student = repo.findByName(name);
            return student;

        }catch (IllegalArgumentException e){
            throw new BusinessException("The Name provided is Null, Please povide a valid name "+e.getMessage());
        }catch(NoSuchElementException e){
            throw  new BusinessException("There is no Element with the given Name"+e.getMessage());
        }

    }

}
