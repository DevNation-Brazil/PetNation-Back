package DevNation.PetNation.Security.Services;

import DevNation.PetNation.Security.Models.User;
import DevNation.PetNation.Security.Models.UserDTO;
import DevNation.PetNation.Security.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User cadastrarUser(@RequestBody UserDTO userDTO){

        User novoUser = new User();
        novoUser.setEmail(userDTO.getEmail());
        novoUser.setNome(userDTO.getNome());
        novoUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));

        userRepository.save(novoUser);

        return novoUser;
    }
}
