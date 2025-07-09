package luis.goes.eval.studentapieval.core.helpers;

import lombok.Getter;

@Getter
public enum ConstraintName {
    UK_STUDENT_NAME("uk_student_name", "name"),
    UK_STUDENT_CPF("uk_student_cpf", "cpf"),
    UK_STUDENT_EMAIL("uk_student_email", "email");

    private final String constraint;
    private final String field;

    ConstraintName(String constraint, String field) {
        this.constraint = constraint;
        this.field = field;
    }

}