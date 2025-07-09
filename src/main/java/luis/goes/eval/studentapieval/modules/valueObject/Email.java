package luis.goes.eval.studentapieval.modules.valueObject;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.core.exception.HttpException;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public final class Email {
    private String email;

    private static final Pattern REGEX = Pattern.compile(
            "^(?=.{1,254}$)(?=.{1,64}@)[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
                    "([a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+" +
                    "[a-zA-Z]{2,}$"
    );

    public Email(String email) {
        this.email = validate(email);
    }

    private String validate(String email) {
        if (email == null) throw HttpException.badRequest("Email must not be null.");

        if (StringUtils.isBlank(email)) throw HttpException.badRequest("Email must no be blank.");

        if (!REGEX.matcher(email).matches()) throw HttpException.badRequest("Invalid email address format.");

        return email.strip();
    }

    public String getValue() {
        return email;
    }

}