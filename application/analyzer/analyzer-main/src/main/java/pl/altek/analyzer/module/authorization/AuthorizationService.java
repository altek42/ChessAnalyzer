package pl.altek.analyzer.module.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.altek.analyzer.auth.AppUserDetails;
import pl.altek.analyzer.auth.JwtToken;
import pl.altek.analyzer.auth.JwtTokenManager;
import pl.altek.analyzer.dozer.Dozer;
import pl.altek.analyzer.openapi.model.SigninAction;
import pl.altek.analyzer.openapi.model.UserEntry;

@Service
public class AuthorizationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private Dozer dozer;

    public UserEntry signin(SigninAction body) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        body.getLogin(),
                        body.getPassword()
                ));
        AppUserDetails principal = (AppUserDetails) authenticate.getPrincipal();
        return dozer.map(principal.getUserEntity(), UserEntry.class);
    }

    public String generateToken(UserEntry userEntry) {
        JwtToken token = new JwtToken();
        token.setUserId(userEntry.getId());
        return "Bearer " + jwtTokenManager.encodeTokenJwt(token);
    }
}
