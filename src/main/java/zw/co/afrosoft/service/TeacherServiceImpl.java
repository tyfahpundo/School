package zw.co.afrosoft.service;

import org.springframework.stereotype.Service;
import zw.co.afrosoft.domain.Teacher;
import zw.co.afrosoft.domain.dto.request.TeacherDetailsRequest;
import zw.co.afrosoft.domain.dto.response.TeacherResponse;
import zw.co.afrosoft.persistence.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService{
    private final TeacherRepository repo;

    public TeacherServiceImpl(TeacherRepository repo) {
        this.repo = repo;
    }

    @Override
    public TeacherResponse createTeacher(TeacherDetailsRequest teacherDetailsRequest) {
        //Creating a Teacher
        Teacher teacher = new Teacher();
        teacher.setName(teacherDetailsRequest.getName());
        teacher.setSurname(teacherDetailsRequest.getSurname());
        teacher.setAge(teacherDetailsRequest.getAge());
        teacher.setLevel(teacherDetailsRequest.getTeacherLevel());
        teacher.setStatus(teacherDetailsRequest.getTeacherStatus());
        teacher.setAddress(teacherDetailsRequest.getAddress());
        teacher.setContactDetails(teacherDetailsRequest.getContactDetail());
        teacher = repo.save(teacher);
        //Mapping the teacher to Response

        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setName(teacher.getName());
        teacherResponse.setSurname(teacher.getSurname());
        teacherResponse.setAge(teacher.getAge());
        teacherResponse.setTeacherLevel(teacher.getLevel());
        teacherResponse.setTeacherStatus(teacher.getStatus());
        teacherResponse.setAddress(teacher.getAddress());
        teacherResponse.setContactDetail(teacher.getContactDetails());

        return teacherResponse;
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return repo.findById(teacherId).get();
    }
}
