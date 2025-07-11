package luis.goes.eval.studentapieval.modules.student.application.useCase.changeCpf;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeCpfDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentChangeCpf implements IStudentChangeCpf {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentChangeCpf(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto change(StudentChangeCpfDto dto) {
        if (dto.id() == null) throw HttpException.badRequest("The ID must not be null.");

        checkIfCpfAlreadyExists(dto.cpf());

        StudentEntity student = repository.findById(dto.id())
                .orElseThrow(() -> HttpException.notFound("Student not found with the given ID."));

        student.changeCpf(dto.cpf());

        return mapper.toDto(repository.save(student));
    }

    private void checkIfCpfAlreadyExists(String cpf) {
        if (repository.findByStudentInfo_Cpf_Cpf(cpf).isPresent()) throw HttpException.conflict("Already has a student with this cpf.");
    }

}