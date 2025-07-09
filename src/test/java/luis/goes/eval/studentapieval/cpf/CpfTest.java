package luis.goes.eval.studentapieval.cpf;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.valueObject.Cpf;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CpfTest {

    @Test
    void shouldCreateValidCpf() {
        final String validCpf = "30126593078";
        Cpf cpf = new Cpf(validCpf);
        assertEquals(validCpf, cpf.getValue());
    }

    @Test
    void shouldThrowExceptionForCpfWithLessThan11Digits() {
        final String invalidCpf = "123456789";

        Exception exception = assertThrows(HttpException.class, () -> new Cpf(invalidCpf));

        assertThat(exception.getMessage(), CoreMatchers.containsString("CPF must have 11 digits"));
    }

    @Test
    void shouldThrowExceptionForCpfWithMoreThan11Digits() {
        final String invalidCpf = "123456789012";

        Exception exception = assertThrows(HttpException.class, () -> new Cpf(invalidCpf));

        assertThat(exception.getMessage(), CoreMatchers.containsString("CPF must have 11 digits"));
    }

    @Test
    void shouldThrowExceptionForCpfWithAllSameDigits() {
        final String invalidCpf = "11111111111";

        Exception exception = assertThrows(HttpException.class, () -> new Cpf(invalidCpf));

        assertThat(exception.getMessage(), CoreMatchers.containsString("Invalid CPF pattern"));
    }

    @Test
    void shouldThrowExceptionForCpfWithInvalidCheckDigits() {
        final String invalidCpf = "12345678900";

        Exception exception = assertThrows(HttpException.class, () -> new Cpf(invalidCpf));

        assertThat(exception.getMessage(), CoreMatchers.containsString("CPF check digits are invalid"));
    }

    @Test
    void shouldThrowExceptionWhenCpfIsNull() {
        Exception exception = assertThrows(HttpException.class, () -> new Cpf(null));

        assertThat(exception.getMessage(), CoreMatchers.containsString("CPF is required"));
    }

    @Test
    void shouldAcceptCpfWithDotsAndDash() {
        final String formattedCpf = "301.265.930-78";
        final String cleanCpf = "30126593078";

        Cpf cpf = new Cpf(formattedCpf);

        assertEquals(cleanCpf, cpf.getValue());
    }

}