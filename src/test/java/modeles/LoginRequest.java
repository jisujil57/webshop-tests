package modeles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

    String email;
    String password;
    String rememberMe;
}
