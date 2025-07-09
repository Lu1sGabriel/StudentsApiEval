package luis.goes.eval.studentapieval.modules.grade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "grade_entity")
@NoArgsConstructor
@Getter
public class GradeEntity {

    @Id
    private UUID id;

    @Column(name = "grade", nullable = false, precision = 3, scale = 2)
    private BigDecimal grade;

    public GradeEntity(UUID id, BigDecimal grade) {
        this.id = UUID.randomUUID();
        this.grade = grade;
    }

    public void changeGrade(BigDecimal grade) {
        this.grade = grade;
    }

}