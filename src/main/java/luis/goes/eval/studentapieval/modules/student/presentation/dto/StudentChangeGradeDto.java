package luis.goes.eval.studentapieval.modules.student.presentation.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record StudentChangeGradeDto(
        UUID id,
        BigDecimal grade
) {
}