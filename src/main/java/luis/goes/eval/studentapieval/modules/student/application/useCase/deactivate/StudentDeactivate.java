package luis.goes.eval.studentapieval.modules.student.application.useCase.deactivate;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentDeactivate implements IStudentDeactivate {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentDeactivate(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void deactivate(UUID id) {
        if (id == null) throw HttpException.badRequest("ID must not be null");
        StudentEntity student = repository.findById(id)
                .orElseThrow(() -> HttpException.notFound("Student not found with the given ID."));
        if (student.getStudentDateInfo().getDeletedAt() != null) throw HttpException.badRequest("This student is already deactivated.");
        student.getStudentDateInfo().deactivate();
        repository.save(student);
    }

}