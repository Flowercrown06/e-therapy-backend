package az.nexus.etherapybackend.service;

import az.nexus.etherapybackend.dto.request.PsychologistRequest;
import az.nexus.etherapybackend.dto.response.PsychologistResponse;

import java.util.List;

public interface PsychologistService {
    List<PsychologistResponse> getAll();
    PsychologistResponse getById(Long id);
    void create(PsychologistRequest request);
    void update(Long id, PsychologistRequest request);
    void delete(Long id) ;

}
