package zw.co.afrosoft.service;

import zw.co.afrosoft.domain.Teacher;
import zw.co.afrosoft.domain.dto.request.TeacherDetailsRequest;
import zw.co.afrosoft.domain.dto.request.UpdateTeacherRequest;
import zw.co.afrosoft.domain.dto.response.TeacherResponse;
import zw.co.afrosoft.util.MessageResponse;

import java.util.List;

public interface TeacherService {
    TeacherResponse createTeacher(TeacherDetailsRequest teacherDetailsRequest);

    Teacher getTeacherById(Long teacherId);

    List<Teacher> getAllTeachers();

    List<Teacher> getAllActiveTeachers();

    MessageResponse deactivateTeacher(Long id);

    MessageResponse activateTeacher(Long id);

    List<Teacher> getAllInactiveTeachers();

    TeacherResponse updateTeacher(Long id, UpdateTeacherRequest updateTeacherRequest);
}
