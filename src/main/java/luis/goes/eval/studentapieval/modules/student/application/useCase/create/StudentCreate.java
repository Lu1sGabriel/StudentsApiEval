package luis.goes.eval.studentapieval.modules.student.application.useCase.create;

import luis.goes.eval.studentapieval.core.shared.utils.EmailGenerator;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.domain.StudentInfo;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentRequestDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StudentCreate implements IStudentCreate {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentCreate(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto create(final StudentRequestDto dto) {
        String email = generateStudentEmail(dto.name());
        StudentInfo studentInfo = createStudentInfo(dto.name(), dto.cpf(), email);
        StudentEntity student = new StudentEntity(studentInfo, dto.grade());
        return mapper.toDto(repository.save(student));
    }

    private StudentInfo createStudentInfo(String name, String cpf, String email) {
        return new StudentInfo(name, cpf, email);
    }

    private String generateStudentEmail(String fullName) {
        Set<String> emailSet = new HashSet<>(repository.findAllEmails());
        return EmailGenerator.generateFromName(fullName, emailSet);
    }

}