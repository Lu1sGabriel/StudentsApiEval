package luis.goes.eval.studentapieval.modules.student.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.modules.valueObject.Cpf;
import luis.goes.eval.studentapieval.modules.valueObject.Email;
import luis.goes.eval.studentapieval.modules.valueObject.Name;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class StudentInfo {

    @Column(name = "name", nullable = false, unique = true)
    private Name name;

    @Column(name = "cpf", nullable = false, unique = true)
    private Cpf cpf;

    @Column(name = "email", nullable = false, unique = true)
    private Email email;

    public StudentInfo(Name name, Cpf cpf, Email email) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
    }


}