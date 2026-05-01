package az.nexus.etherapybackend.mapper;


import az.nexus.etherapybackend.dto.response.ReservationResponse;
import az.nexus.etherapybackend.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "psychologistFullName", expression = "java(r.getPsychologist().getName() + \" \" + r.getPsychologist().getSurname())")
    @Mapping(target = "userFullName", expression = "java(r.getUser().getName() + \" \" + r.getUser().getSurname())")
    ReservationResponse toResponse(Reservation r);
}