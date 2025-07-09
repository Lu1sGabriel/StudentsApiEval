package luis.goes.eval.studentapieval.core.shared.mapper;


import luis.goes.eval.studentapieval.core.helpers.ConstraintName;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ConstraintNameMapper {

    private static final Map<String, String> CONSTRAINT_TO_FIELD = Arrays.stream(ConstraintName.values())
            .collect(Collectors.toMap(ConstraintName::getConstraint, ConstraintName::getField));

    public static String getFieldName(String constraintName) {
        return CONSTRAINT_TO_FIELD.getOrDefault(constraintName, "unknown");
    }

}