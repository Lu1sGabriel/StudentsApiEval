package luis.goes.eval.studentapieval.modules.student.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.modules.grade.GradeEntity;
import org.hibernate.proxy.HibernateProxy;

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
public class StudentEntity {

    @Id
    private UUID id;

    @Embedded
    private StudentInfo studentInfo;

    @OneToOne(fetch = FetchType.LAZY)
    private GradeEntity grade;

    public StudentEntity(UUID id, StudentInfo studentInfo, GradeEntity grade) {
        this.id = UUID.randomUUID();
        this.studentInfo = studentInfo;
        this.grade = grade;
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