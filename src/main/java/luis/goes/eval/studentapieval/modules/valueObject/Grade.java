package luis.goes.eval.studentapieval.modules.valueObject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.core.exception.HttpException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public final class Grade {

    private BigDecimal grade;

    public Grade(BigDecimal grade) {
        this.grade = validate(grade);
    }

    private BigDecimal validate(BigDecimal value) {
        if (value == null) {
            throw HttpException.badRequest("Grade must not be null.");
        }

        if (value.compareTo(BigDecimal.ZERO) < 0 || value.compareTo(new BigDecimal("10.00")) > 0) {
            throw HttpException.badRequest("Grade must be between 0 and 10.");
        }

        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getValue() {
        return grade;
    }

}