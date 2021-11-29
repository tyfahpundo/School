package zw.co.afrosoft.service;

import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.Teacher;
import zw.co.afrosoft.domain.dto.request.TeacherDetailsRequest;
import zw.co.afrosoft.domain.dto.request.UpdateTeacherRequest;
import zw.co.afrosoft.domain.dto.response.TeacherResponse;
import zw.co.afrosoft.domain.enums.TeacherStatus;
import zw.co.afrosoft.persistence.TeacherRepository;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository repo;

    public TeacherServiceImpl(TeacherRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeacherResponse createTeacher(TeacherDetailsRequest teacherDetailsRequest) {
        //Creating a Teacher
        Teacher teacher = new Teacher().builder()
                .name(teacherDetailsRequest.getName())
                .surname(teacherDetailsRequest.getSurname())
                .age(teacherDetailsRequest.getAge())
                .level(teacherDetailsRequest.getTeacherLevel())
                .status(teacherDetailsRequest.getTeacherStatus())
                .address(teacherDetailsRequest.getAddress())
                .contactDetails(teacherDetailsRequest.getContactDetail())
                .build();
        teacher = repo.save(teacher);

        //Mapping the teacher to Response
        TeacherResponse teacherResponse = new TeacherResponse().builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .age(teacher.getAge())
                .teacherLevel(teacher.getLevel())
                .teacherStatus(teacher.getStatus())
                .address(teacher.getAddress())
                .contactDetail(teacher.getContactDetails())
                .build();
        return teacherResponse;
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return repo.findById(teacherId).get();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return repo.findAll();
    }

    @Override
    public List<Teacher> getAllActiveTeachers() {
        return repo.findAll()
                .stream()
                .filter(teacher -> teacher.getStatus() == TeacherStatus.ACTIVE)
                .collect(Collectors.toList());
    }
    @Override
    public List<Teacher> getAllInactiveTeachers() {
        return repo.findAll()
                .stream()
                .filter(teacher -> teacher.getStatus() == TeacherStatus.INACTIVE)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponse updateTeacher(Long id, UpdateTeacherRequest updateTeacherRequest) {
        Teacher updatedTeacher = repo.findById(id).get();
        updatedTeacher.builder()
                .name(updatedTeacher.getName())
                .surname(updateTeacherRequest.getSurname())
                .age(updatedTeacher.getAge())
                .level(updatedTeacher.getLevel())
                .status(updatedTeacher.getStatus())
                .address(updateTeacherRequest.getAddress())
                .contactDetails(updateTeacherRequest.getContactDetail())
                .build();
        updatedTeacher = repo.save(updatedTeacher);

        TeacherResponse teacherResponse = new TeacherResponse().builder()
                .name(updatedTeacher.getName())
                .surname(updatedTeacher.getSurname())
                .age(updatedTeacher.getAge())
                .teacherLevel(updatedTeacher.getLevel())
                .teacherStatus(updatedTeacher.getStatus())
                .address(updateTeacherRequest.getAddress())
                .contactDetail(updatedTeacher.getContactDetails())
                .build();
        return  teacherResponse;
    }

    @Override
    public MessageResponse deactivateTeacher(Long id) {
        Teacher teacher = repo.findById(id).get();
        if(teacher.getStatus() == TeacherStatus.ACTIVE){
            teacher.setStatus(TeacherStatus.INACTIVE);
            this.repo.save(teacher);
            return MessageResponse.createMessageResponse("TEACHER SUCCESSFULLY DE-ACTIVATED!!");
        }else{
            return MessageResponse.createMessageResponse("TEACHER IS ALREADY INACTIVE!!");
        }
    }

    @Override
    public MessageResponse activateTeacher(Long id) {
        Teacher teacher = repo.findById(id).get();
        if(teacher.getStatus() == TeacherStatus.INACTIVE){
            teacher.setStatus(TeacherStatus.ACTIVE);
            this.repo.save(teacher);
            return MessageResponse.createMessageResponse("TEACHER SUCCESSFULLY ACTIVATED!!");
        }else{
            return MessageResponse.createMessageResponse("TEACHER IS ALREADY ACTIVE!!!!");
        }
    }


}
