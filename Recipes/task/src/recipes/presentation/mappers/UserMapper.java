package recipes.presentation.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import recipes.businesslayer.entities.User;
import recipes.presentation.dtos.UserInfo;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toUser(UserInfo userInfo) {
        String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
        return new User(userInfo.getEmail(), encodedPassword);
    }
}
