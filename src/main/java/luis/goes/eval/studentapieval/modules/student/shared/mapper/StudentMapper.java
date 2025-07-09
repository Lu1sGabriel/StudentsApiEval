package luis.goes.eval.studentapieval.modules.student.shared.mapper;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.core.shared.mapper.entityToDto.Mapper;
import luis.goes.eval.studentapieval.modules.student.domain.StudentEntity;
import luis.goes.eval.studentapieval.modules.student.presentation.dto.StudentResponseDto;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentMapper implements Mapper<StudentResponseDto, StudentEntity> {

    @Override
    public StudentResponseDto toDto(StudentEntity studentEntity) {
        return new StudentResponseDto(
                studentEntity.getId(),
                studentEntity.getGrade().getValue(),
                studentEntity.getStudentInfo().getName().getValue(),
                getFirstLetterThatDoesNotRepeat(studentEntity.getStudentInfo().getName().getValue().strip()),
                studentEntity.getStudentInfo().getCpf().getValue(),
                studentEntity.getStudentInfo().getEmail().getValue(),
                studentEntity.getStudentDateInfo().getCreatedAt(),
                studentEntity.getStudentDateInfo().getUpdatedAt()
        );
    }

    @Override
    public List<StudentResponseDto> toDtoList(List<StudentEntity> studentEntities) {
        return studentEntities.stream().map(this::toDto).toList();
    }

    private Character getFirstLetterThatDoesNotRepeat(String name) {
        if (name == null || name.isBlank()) throw HttpException.badRequest("Name must not be null or blank. ");

        final Map<Character, Integer> frequency = new LinkedHashMap<>();

        for (char character : name.toLowerCase().toCharArray()) {
            frequency.put(character, frequency.getOrDefault(character, 0) + 1);
        }

        for (char character : name.toLowerCase().toCharArray()) {
            if (frequency.get(character) == 1) {
                return character;
            }
        }

        return '_';
    }

}