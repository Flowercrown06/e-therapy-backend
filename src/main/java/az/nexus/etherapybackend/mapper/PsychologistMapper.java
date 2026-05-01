package az.nexus.etherapybackend.mapper;

import az.nexus.etherapybackend.dto.request.PsychologistRequest;
import az.nexus.etherapybackend.dto.response.PsychologistResponse;
import az.nexus.etherapybackend.entity.Psychologist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PsychologistMapper {

    PsychologistResponse toResponse(Psychologist psychologist);

     //  User əlaqesini Service-de idare etmek daha tehlukesizdir ama diger saheler avtomatik kocurulur.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Psychologist toEntity(PsychologistRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromRequest(PsychologistRequest request, @MappingTarget Psychologist psychologist);
}