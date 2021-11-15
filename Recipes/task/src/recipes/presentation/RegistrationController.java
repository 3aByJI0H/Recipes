package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.entities.User;
import recipes.persistence.UserRepository;
import recipes.presentation.dtos.UserInfo;
import recipes.presentation.mappers.UserMapper;

import javax.validation.Valid;

@RestController
@RequestMapping("api/register")
public class RegistrationController {

    private UserMapper userMapper;
    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @PostMapping
    public void register(@Valid @RequestBody UserInfo userInfo) {
        if ( userRepository.existsById(userInfo.getEmail()) ) {
            String message = "User with email " + userInfo.getEmail() + " already exists!";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }

        User user = userMapper.toUser(userInfo);
        userRepository.save(user);
    }
}