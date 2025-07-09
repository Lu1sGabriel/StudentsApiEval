package luis.goes.eval.studentapieval.modules.student.application.useCase.changeEmail;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeEmailDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentChangeEmail implements IStudentChangeEmail {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentChangeEmail(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto change(StudentChangeEmailDto dto) {
        checkIfEmailAlreadyExists(dto.email());

        StudentEntity student = repository.findById(dto.id())
                .orElseThrow(() -> HttpException.notFound("Student not found with the given ID."));

        student.changeEmail(dto.email().strip());

        return mapper.toDto(repository.save(student));
    }

    private void checkIfEmailAlreadyExists(String email) {
        if (repository.findByStudentInfo_Email_Email(email).isPresent()) throw HttpException.conflict("Already has a student with this email.");
    }

}