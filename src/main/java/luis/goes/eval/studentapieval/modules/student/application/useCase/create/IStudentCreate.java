package luis.goes.eval.studentapieval.modules.student.application.useCase.create;

import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentRequestDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;

public interface IStudentCreate {

    StudentResponseDto create(StudentRequestDto dto);

}