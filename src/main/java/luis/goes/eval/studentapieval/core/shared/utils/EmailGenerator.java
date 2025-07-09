package luis.goes.eval.studentapieval.core.shared.utils;

import luis.goes.eval.studentapieval.core.exception.HttpException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class EmailGenerator {

    private static final String DOMAIN = "@example.me";
    private static final Set<String> PREPOSITIONS = Set.of("de", "da", "do", "das", "dos");

    public static String generateFromName(String fullName, Set<String> existingEmails) {
        if (fullName == null || fullName.isBlank()) {
            throw HttpException.badRequest("Name is required to generate email.");
        }

        String[] parts = fullName.trim().toLowerCase().split("\\s+");
        List<String> filtered = Arrays.stream(parts)
                .filter(p -> !PREPOSITIONS.contains(p))
                .toList();

        if (filtered.size() < 2) {
            throw HttpException.badRequest("Name must contain at least two valid parts.");
        }

        String first = filtered.get(0);
        String second = filtered.size() > 2 ? filtered.get(1) : "";
        String last = filtered.get(filtered.size() - 1);

        String base = first + second + "." + last;
        String email = base + DOMAIN;

        int suffix = 1;
        while (existingEmails.contains(email)) {
            email = base + suffix + DOMAIN;
            suffix++;
        }

        return email;
    }

}