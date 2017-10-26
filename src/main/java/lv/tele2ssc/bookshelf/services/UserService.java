package lv.tele2ssc.bookshelf.services;

import lv.tele2ssc.bookshelf.repositories.RoleRepository;
import lv.tele2ssc.bookshelf.repositories.UserRepository;
import lv.tele2ssc.bookshelf.model.User;
import lv.tele2ssc.bookshelf.model.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email.toLowerCase());
    }
    
    public Role findRole(String name) {
        return roleRepository.findByName(name);
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public User findUser(Long userId) {
        return userRepository.findOne(userId);
    }
    
    public void save(User user) {
        Role usersRole = findRole("user");
        Set<Role> roleSet = new HashSet<>();

        // making sure roles preserved if user is about to update
        if (user.getId() != null) {
            User existing = userRepository.findOne(user.getId());
            roleSet.addAll(existing.getRoles());
        }
        
        roleSet.add(usersRole);

        user.setRoles(roleSet);
        userRepository.save(user);
    }
   
    
}
