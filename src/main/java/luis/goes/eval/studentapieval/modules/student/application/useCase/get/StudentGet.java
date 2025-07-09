package luis.goes.eval.studentapieval.modules.student.application.useCase.get;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentGet implements IStudentGet {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentGet(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto getById(UUID id) {
        StudentEntity student = repository.findById(id)
                .orElseThrow(() -> HttpException.notFound("Student not found with the given ID."));
        if (student.getStudentDateInfo().getDeletedAt() != null) throw HttpException.badRequest("This student is deactivated.");
        return mapper.toDto(student);
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<StudentEntity> studentList = repository.findByStudentDateInfo_DeletedAtIsNull(Sort.by(Sort.Direction.ASC, "name"));
        return mapper.toDtoList(studentList);
    }

    @Override
    public StudentResponseDto getByName(String name) {
        StudentEntity student = repository.findByStudentInfo_Name_Name(name)
                .orElseThrow(() -> HttpException.notFound("Student not found with the given name."));
        return mapper.toDto(student);
    }

    @Override
    public StudentResponseDto getByCpf(String cpf) {
        StudentEntity student = repository.findByStudentInfo_Cpf_Cpf(cpf)
                .orElseThrow(() -> HttpException.notFound("Student not found with the given cpf."));
        return mapper.toDto(student);
    }

    @Override
    public StudentResponseDto getByEmail(String email) {
        StudentEntity student = repository.findByStudentInfo_Email_Email(email)
                .orElseThrow(() -> HttpException.notFound("Student not found with the given email."));
        return mapper.toDto(student);
    }

}