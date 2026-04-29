package az.nexus.etherapybackend.dto.request;

import java.util.List;

public record TestSubmission(
        Long testId,
        List<AnswerSelection> selections
) {}