package pl.altek.chessanalizer.module.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.altek.chessanalizer.openapi.api.AuthorizationApiDelegate;
import pl.altek.chessanalizer.openapi.model.SigninAction;
import pl.altek.chessanalizer.openapi.model.UserEntry;

@Service
public class AuthorizationDelegate implements AuthorizationApiDelegate {

    @Autowired
    private AuthorizationService authorizationService;


    @Override
    public ResponseEntity<UserEntry> signin(SigninAction body) {
        UserEntry userEntry = authorizationService.signin(body);
        String token = authorizationService.generateToken(userEntry);

        return ResponseEntity.ok()
                .header(
                    HttpHeaders.AUTHORIZATION,
                    token
                )
                .body(userEntry);
    }
}
