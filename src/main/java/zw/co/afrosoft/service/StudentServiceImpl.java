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
            Student newStudent = new Student();
            //Mapping new student details from the request
            newStudent.setName(studentDetailsRequest.getName());
            newStudent.setSurname(studentDetailsRequest.getSurname());
            newStudent.setAge(studentDetailsRequest.getAge());
            newStudent.setStudentLevel(studentDetailsRequest.getStudentLevel());
            newStudent.setStudentStatus(studentDetailsRequest.getStudentStatus());
            newStudent.setContactDetail(studentDetailsRequest.getContactDetail());
            newStudent.setAddress(studentDetailsRequest.getAddress());
            newStudent = repo.save(newStudent);

            //Mapping student details to the response
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(newStudent.getStudentId());
            studentResponse.setName(newStudent.getName());
            studentResponse.setSurname(newStudent.getSurname());
            studentResponse.setAge(newStudent.getAge());
            studentResponse.setStudentLevel(newStudent.getStudentLevel());
            studentResponse.setStudentStatus(newStudent.getStudentStatus());
            studentResponse.setContactDetail(newStudent.getContactDetail());
            studentResponse.setAddress(newStudent.getAddress());
            //studentResponse.setEnrolledSubjects(newStudent.getSubjects());

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
            student.setStudentStatus(StudentStatus.INACTIVE);
            this.repo.save(student);
            return MessageResponse.createMessageResponse("STUDENT SUCCESSFULLY TERMINATED");
        }catch (Exception e){
            throw new BusinessException("Something went wrong in the Service Layer while terminating the Student");
        }

    }

    @Override
    public MessageResponse reinstateStudent(Long id) {
        try {
            Student student = repo.findById(id).get();
            student.setStudentStatus(StudentStatus.ACTIVE);
            this.repo.save(student);
            return MessageResponse.createMessageResponse("STUDENT SUCCESSFULLY RE_INSTATED");
        }catch(Exception e){
            throw new BusinessException("Something went wrong in the Service Layer while reinstating the student");
        }
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentUpdateRequest studentUpdateRequest) {
        try {
            Student updateStudent = repo.findById(id).get();
            updateStudent.setName(studentUpdateRequest.getName());
            updateStudent.setSurname(studentUpdateRequest.getSurname());
            updateStudent.setAge(studentUpdateRequest.getAge());
            updateStudent.setContactDetail(studentUpdateRequest.getContactDetail());
            updateStudent.setAddress(studentUpdateRequest.getAddress());
            updateStudent = repo.save(updateStudent);

            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setId(updateStudent.getStudentId());
            studentResponse.setName(updateStudent.getName());
            studentResponse.setSurname(updateStudent.getSurname());
            studentResponse.setAge(updateStudent.getAge());
            studentResponse.setStudentLevel(updateStudent.getStudentLevel());
            studentResponse.setStudentStatus(updateStudent.getStudentStatus());
            studentResponse.setContactDetail(updateStudent.getContactDetail());
            studentResponse.setAddress(updateStudent.getAddress());

            return studentResponse;
        }catch (Exception e){
            throw new BusinessException("Something went wrong in the Service Layer while updating the Student");
        }
    }

}
