package DevNation.PetNation.Services;

import DevNation.PetNation.Models.User;
import DevNation.PetNation.Repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserService implements UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDetailsRepository.findByUserName(username);

        if (null == user) {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
        return user;
    }
}
