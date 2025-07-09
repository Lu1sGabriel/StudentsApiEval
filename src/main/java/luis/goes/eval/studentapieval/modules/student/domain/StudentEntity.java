package luis.goes.eval.studentapieval.modules.student.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.core.shared.mapper.entityToDto.Mappable;
import luis.goes.eval.studentapieval.modules.valueObject.Grade;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "student_db",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_student_name", columnNames = {"name"}),
                @UniqueConstraint(name = "uk_student_cpf", columnNames = {"cpf"}),
                @UniqueConstraint(name = "uk_student_email", columnNames = {"email"})
        })
@NoArgsConstructor
@Getter
public class StudentEntity implements Mappable {

    @Id
    private UUID id;

    @Embedded
    @AttributeOverride(name = "grade", column = @Column(name = "grade", nullable = false, precision = 4, scale = 2))
    private Grade grade;

    @Embedded
    private StudentInfo studentInfo;

    @Embedded
    private StudentDateInfo studentDateInfo;

    public StudentEntity(StudentInfo studentInfo, BigDecimal grade) {
        this.id = UUID.randomUUID();
        this.grade = new Grade(grade);
        this.studentInfo = studentInfo;
        this.studentDateInfo = new StudentDateInfo();
    }

    public void changeGrade(BigDecimal grade) {
        this.grade = new Grade(grade);
        this.studentDateInfo.update();
    }

    public void changeName(String name) {
        this.studentInfo.changeName(name);
        this.studentDateInfo.update();
    }

    public void changeCpf(String cpf) {
        this.studentInfo.changeCpf(cpf);
        this.studentDateInfo.update();
    }

    public void changeEmail(String email) {
        this.studentInfo.changeEmail(email);
        this.studentDateInfo.update();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() :
                this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StudentEntity that = (StudentEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}