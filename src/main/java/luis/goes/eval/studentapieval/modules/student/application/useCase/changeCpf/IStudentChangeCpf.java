package luis.goes.eval.studentapieval.modules.student.application.useCase.changeCpf;

import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeCpfDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;

public interface IStudentChangeCpf {

    StudentResponseDto change(StudentChangeCpfDto dto);

}