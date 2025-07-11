package luis.goes.eval.studentapieval.modules.student.application.useCase.deactivate;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentDeactivate implements IStudentDeactivate {
    private final StudentRepository repository;

    public StudentDeactivate(StudentRepository repository) {
        this.repository = repository;
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