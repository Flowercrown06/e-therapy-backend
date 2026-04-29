package az.nexus.etherapybackend.dto.response;

import az.nexus.etherapybackend.enums.RiskLevel;
import lombok.Builder;

@Builder
public record TestResultResponse(
        Integer totalScore,
        RiskLevel riskLevel,
        String recommendation
) {}
