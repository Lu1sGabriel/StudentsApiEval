package luis.goes.eval.studentapieval.modules.student.application.useCase.changeName;

import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeNameDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;

public interface IStudentChangeName {

    StudentResponseDto change(StudentChangeNameDto dto);

}