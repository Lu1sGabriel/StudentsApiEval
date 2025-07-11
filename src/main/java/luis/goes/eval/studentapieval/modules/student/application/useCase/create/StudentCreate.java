package luis.goes.eval.studentapieval.modules.student.application.useCase.create;

import luis.goes.eval.studentapieval.core.exception.HttpException;
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
        checkIfFieldsAlreadyExists(dto);
        String email = generateStudentEmail(dto);
        StudentInfo studentInfo = createStudentInfo(dto, email);
        StudentEntity student = new StudentEntity(studentInfo, dto.grade());
        return mapper.toDto(repository.save(student));
    }

    private StudentInfo createStudentInfo(StudentRequestDto dto, String email) {
        return new StudentInfo(dto.name(), dto.cpf(), email);
    }

    private String generateStudentEmail(StudentRequestDto dto) {
        Set<String> emailSet = new HashSet<>(repository.findAllEmails());
        return EmailGenerator.generateFromName(dto.name(), emailSet);
    }

    private void checkIfFieldsAlreadyExists(StudentRequestDto dto) {
        if (repository.findByStudentInfo_Name_Name(dto.name()).isPresent()) throw HttpException.conflict("Already has a student with this name.");
        if (repository.findByStudentInfo_Cpf_Cpf(dto.cpf()).isPresent()) throw HttpException.conflict("Already has a student with this CPF.");
    }

}