package luis.goes.eval.studentapieval.modules.student.presentation.dto;

import java.util.UUID;

public record StudentChangeNameDto(
        UUID id,
        String name
) {
}