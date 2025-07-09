package luis.goes.eval.studentapieval.modules.student.presentation.dto;

import java.math.BigDecimal;

public record StudentRequestDto(
        String name,
        String cpf,
        BigDecimal grade
) {
}
