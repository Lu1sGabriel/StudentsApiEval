package luis.goes.eval.studentapieval.modules.student.application.useCase.get;

import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;

import java.util.List;
import java.util.UUID;

public interface IStudentGet {

    StudentResponseDto getById(UUID id);

    List<StudentResponseDto> getAll();

    StudentResponseDto getByName(String name);

    StudentResponseDto getByCpf(String cpf);

    StudentResponseDto getByEmail(String email);

}