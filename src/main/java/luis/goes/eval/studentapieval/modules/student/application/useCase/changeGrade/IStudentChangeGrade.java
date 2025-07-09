package luis.goes.eval.studentapieval.modules.student.application.useCase.changeGrade;

import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeGradeDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;

public interface IStudentChangeGrade {

    StudentResponseDto change(StudentChangeGradeDto dto);

}