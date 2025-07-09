package luis.goes.eval.studentapieval.modules.student.presentation.dto;

import luis.goes.eval.studentapieval.core.shared.mapper.entityToDto.DTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record StudentResponseDto(
        UUID id,
        BigDecimal grade,
        String name,
        Character firstLetterThatDontRepeat,
        String cpf,
        String email,
        Instant createdAt,
        Instant updatedAt
) implements DTO {
}