package az.nexus.etherapybackend.auth;

import az.nexus.etherapybackend.entity.User;
import az.nexus.etherapybackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("İstifadəçi tapılmadı: " + email));

         return new MyUserDetails(user);
    }
}