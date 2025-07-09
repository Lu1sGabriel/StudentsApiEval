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

    public StudentInfo(String name, String cpf, String email) {
        this.name = new Name(name);
        this.cpf = new Cpf(cpf);
        this.email = new Email(email);
    }

    public void changeName(String name) {
        this.name = new Name(name);
    }

    public void changeCpf(String cpf) {
        this.cpf = new Cpf(cpf);
    }

    public void changeEmail(String email) {
        this.email = new Email(email);
    }

}