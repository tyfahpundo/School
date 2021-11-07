package zw.co.afrosoft.service;

import zw.co.afrosoft.domain.Teacher;
import zw.co.afrosoft.domain.dto.request.TeacherDetailsRequest;
import zw.co.afrosoft.domain.dto.response.TeacherResponse;

public interface TeacherService {
    TeacherResponse createTeacher(TeacherDetailsRequest teacherDetailsRequest);

    Teacher getTeacherById(Long teacherId);
}
