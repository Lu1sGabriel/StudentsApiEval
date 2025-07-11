package luis.goes.eval.studentapieval.email;

import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.core.shared.utils.EmailGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EmailGeneratorTest {

    @Test
    void shouldGenerateEmailFromFullName() {
        Set<String> existing = new HashSet<>();
        String fullName = "Luis Gabriel Santana";

        String email = EmailGenerator.generateFromName(fullName, existing);

        assertEquals("luisgabriel.santana@example.me", email);
    }

    @Test
    void shouldIgnorePrepositionsInName() {
        Set<String> existing = new HashSet<>();
        String fullName = "Luis de Santana";

        String email = EmailGenerator.generateFromName(fullName, existing);

        assertEquals("luis.santana@example.me", email);
    }

    @Test
    void shouldGenerateEmailWithNumericSuffixIfAlreadyExists() {
        Set<String> existing = new HashSet<>();
        existing.add("luis.santana@example.me");
        existing.add("luis.santana1@example.me");

        String fullName = "Luis Santana";

        String email = EmailGenerator.generateFromName(fullName, existing);

        assertEquals("luis.santana2@example.me", email);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        Set<String> existing = new HashSet<>();

        Exception exception = assertThrows(HttpException.class, () -> EmailGenerator.generateFromName(null, existing));

        assertTrue(exception.getMessage().contains("Name is required to generate email."));
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        Set<String> existing = new HashSet<>();

        Exception exception = assertThrows(HttpException.class, () -> EmailGenerator.generateFromName("   ", existing));

        assertTrue(exception.getMessage().contains("Name is required to generate email."));
    }

    @Test
    void shouldThrowExceptionWhenNameHasOnlyOneValidWord() {
        Set<String> existing = new HashSet<>();

        Exception exception = assertThrows(HttpException.class, () -> EmailGenerator.generateFromName("de Santana", existing));

        assertTrue(exception.getMessage().contains("Name must contain at least two valid parts."));
    }

    @Test
    void shouldUseSecondNameIfExists() {
        Set<String> existing = new HashSet<>();
        String fullName = "Luis Gabriel de Santana";

        String email = EmailGenerator.generateFromName(fullName, existing);

        assertEquals("luisgabriel.santana@example.me", email);
    }

    @Test
    void shouldHandleUppercaseNames() {
        Set<String> existing = new HashSet<>();
        String fullName = "LUIS GABRIEL SANTANA";

        String email = EmailGenerator.generateFromName(fullName, existing);

        assertEquals("luisgabriel.santana@example.me", email);
    }

}