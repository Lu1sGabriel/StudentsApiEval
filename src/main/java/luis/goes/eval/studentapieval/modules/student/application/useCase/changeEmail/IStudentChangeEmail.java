package luis.goes.eval.studentapieval.modules.student.application.useCase.changeEmail;

import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeEmailDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;

public interface IStudentChangeEmail {

    StudentResponseDto change(StudentChangeEmailDto dto);

}