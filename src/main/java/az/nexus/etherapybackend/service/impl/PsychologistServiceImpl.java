package az.nexus.etherapybackend.service.impl;

import az.nexus.etherapybackend.dto.request.PsychologistRequest;
import az.nexus.etherapybackend.dto.response.PsychologistResponse;
import az.nexus.etherapybackend.entity.Psychologist;
import az.nexus.etherapybackend.entity.User;
import az.nexus.etherapybackend.mapper.PsychologistMapper;
import az.nexus.etherapybackend.repository.PsychologistRepository;
import az.nexus.etherapybackend.repository.UserRepository;
import az.nexus.etherapybackend.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final UserRepository userRepository;
    private final PsychologistMapper psychologistMapper;


    @Transactional(readOnly = true)
    public List<PsychologistResponse> getAll() {
        return psychologistRepository.findAll()
                .stream()
                .map(psychologistMapper::toResponse)
                .toList();
    }


    @Transactional(readOnly = true)
    public PsychologistResponse getById(Long id) {
        return psychologistRepository.findById(id)
                .map(psychologistMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Psixoloq tapılmadı ID: " + id));
    }


    @Transactional
    public void create(PsychologistRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("Göstərilən User ID mövcud deyil"));

        Psychologist psychologist = psychologistMapper.toEntity(request);

        psychologist.setUser(user);
        psychologist.setRating(0.0); // ilk baslangic reytinq

        psychologistRepository.save(psychologist);
    }


    @Transactional
    public void update(Long id, PsychologistRequest request) {
        Psychologist psychologist = psychologistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yenilənəcək psixoloq tapılmadı"));

        psychologistMapper.updateEntityFromRequest(request, psychologist);

        psychologistRepository.save(psychologist);
    }

    public void delete(Long id) {
        if (!psychologistRepository.existsById(id)) {
            throw new RuntimeException("Silinəcək psixoloq tapılmadı");
        }
        psychologistRepository.deleteById(id);
    }
}
