package pl.altek.chessanalizer.module.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.auth.AppUserDetails;
import pl.altek.chessanalizer.db.domain.user.UserEntity;

import java.util.UUID;

@Service
public class UserService {

    public UserEntity getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUserDetails principal = (AppUserDetails)authentication.getPrincipal();
        return principal.getUserEntity();
    }

    public UUID getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public UUID getCurrentUserChessId() {
        return getCurrentUser().getChessId();
    }
}
