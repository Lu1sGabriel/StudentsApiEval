package luis.goes.eval.studentapieval.modules.student.application.useCase.changeName;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.core.shared.utils.EmailGenerator;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeNameDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentChangeName implements IStudentChangeName {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentChangeName(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto change(StudentChangeNameDto dto) {
        checkIfNameAlreadyExists(dto.name());

        StudentEntity student = repository.findById(dto.id())
                .orElseThrow(() -> HttpException.notFound("Student not found with the given ID."));

        student.changeName(dto.name());

        student.changeEmail(generateEmailFromName(dto.name()));

        return mapper.toDto(repository.save(student));
    }

    private void checkIfNameAlreadyExists(String name) {
        if (repository.findByStudentInfo_Name_Name(name).isPresent()) throw HttpException.conflict("Already has a student with this name.");
    }

    private String generateEmailFromName(String name) {
        Set<String> emailSet = new HashSet<>(repository.findAllEmails());
        return EmailGenerator.generateFromName(name, emailSet);
    }

}