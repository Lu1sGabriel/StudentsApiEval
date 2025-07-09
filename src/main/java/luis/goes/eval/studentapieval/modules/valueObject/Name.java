package luis.goes.eval.studentapieval.modules.valueObject;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import luis.goes.eval.studentapieval.core.exception.HttpException;
import luis.goes.eval.studentapieval.core.shared.utils.NameFormatter;

import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class Name {
    private String name;
    private static final Pattern REGEX = Pattern.compile(
            "^[a-zà-öø-ÿ\\s]+$", Pattern.CASE_INSENSITIVE
    );

    public Name(String name) {
        this.name = validate(name);
    }

    private String validate(String name) {
        if (name == null) throw HttpException.badRequest("Name is required.");

        if (StringUtils.isBlank(name)) throw HttpException.badRequest("Name cannot be empty or whitespace.");

        if (name.length() < 2) throw HttpException.badRequest("Name must be at least 2 characters long.");

        if (!REGEX.matcher(name).matches()) throw HttpException.badRequest("Name can only contain letters and spaces.");

        return NameFormatter.format(name);
    }

    public String getValue() {
        return name;
    }

}