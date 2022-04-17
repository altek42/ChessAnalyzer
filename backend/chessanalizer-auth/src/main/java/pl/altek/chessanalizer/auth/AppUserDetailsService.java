package pl.altek.chessanalizer.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.db.entity.UserEntity;
import pl.altek.chessanalizer.db.repository.UserRepository;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOp = userRepository.findByUsername(username);
        UserEntity user = userOp.orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
        return new AppUserDetails(user);
    }
}
