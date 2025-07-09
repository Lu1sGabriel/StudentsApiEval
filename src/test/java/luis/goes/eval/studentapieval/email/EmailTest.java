package luis.goes.eval.studentapieval.email;


import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.modules.valueObject.Email;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {
    @Test
    void shouldCreateValidFullName() {
        final String validEmail = "luisgabriel.santana@example.com";
        Email email = new Email(validEmail);
        assertEquals(validEmail, email.getValue());
    }

    @Test
    void shouldThrowExceptionForNullableEmail() {
        Exception exception = assertThrows(HttpException.class, () -> new Email(null));
        assertThat(exception.getMessage(), CoreMatchers.containsString("Email must not be null."));
    }

    @Test
    void shouldThrowExceptionForInvalidEmail() {
        final String invalidEmail = "email.com";
        Exception exception = assertThrows(HttpException.class, () -> new Email(invalidEmail));
        assertThat(exception.getMessage(), CoreMatchers.containsString("Email invalid"));
    }

}
