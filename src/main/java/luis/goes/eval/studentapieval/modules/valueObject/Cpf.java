package luis.goes.eval.studentapieval.modules.valueObject;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.core.exception.HttpException;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class Cpf {

    private String cpf;

    public Cpf(String value) {
        this.cpf = validate(value);
    }

    private String validate(String cpf) {
        if (cpf == null) throw HttpException.badRequest("CPF is required.");

        // Remove caracteres não numéricos
        String numericCpf = cpf.replaceAll("\\D", "");

        if (numericCpf.length() != 11) {
            throw HttpException.badRequest("CPF must have 11 digits.");
        }

        if (!numericCpf.matches("\\d{11}")) {
            throw HttpException.badRequest("CPF must contain only digits.");
        }

        if (isInvalidPattern(numericCpf)) {
            throw HttpException.badRequest("Invalid CPF pattern.");
        }

        if (!isValidCpf(numericCpf)) {
            throw HttpException.badRequest("CPF check digits are invalid.");
        }

        return numericCpf;
    }

    private boolean isInvalidPattern(String cpf) {
        return cpf.chars().distinct().count() == 1;
    }

    private boolean isValidCpf(String cpf) {
        int d1 = calculateDigit(cpf, 10);
        int d2 = calculateDigit(cpf, 11);

        return cpf.charAt(9) == Character.forDigit(d1, 10)
                && cpf.charAt(10) == Character.forDigit(d2, 10);
    }

    private int calculateDigit(String cpf, int weightStart) {
        int sum = 0;
        for (int i = 0; i < weightStart - 1; i++) {
            sum += (cpf.charAt(i) - '0') * (weightStart - i);
        }

        int result = 11 - (sum % 11);
        return (result >= 10) ? 0 : result;
    }

    public String getValue() {
        return cpf;
    }

}