package luis.goes.eval.studentapieval.modules.student.application.useCase.changeGrade;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.infrastructure.StudentRepository;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentChangeGradeDto;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import luis.goes.eval.studentapieval.modules.student.shared.mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentChangeGrade implements IStudentChangeGrade {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentChangeGrade(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StudentResponseDto change(StudentChangeGradeDto dto) {
        StudentEntity student = repository.findById(dto.id())
                .orElseThrow(() -> HttpException.notFound("Student not found with the given ID."));

        student.changeGrade(dto.grade());

        return mapper.toDto(repository.save(student));
    }

}