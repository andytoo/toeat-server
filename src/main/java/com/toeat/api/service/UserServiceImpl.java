package com.toeat.api.service;

import com.toeat.api.Repository.RestaurantRepository;
import com.toeat.api.Repository.UserRepository;
import com.toeat.api.exceptions.EtAuthException;
import com.toeat.api.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public User signIn(String phone, String password) throws EtAuthException {
        User user = userRepository.findByPhone(phone);
        if (BCrypt.checkpw(password, user.getPassword())) {
            return user;
        } else {
            throw new EtAuthException("Password incorrect");
        }
    }

    @Override
    public User signUp(String phone, String name, String password, String confirm) throws EtAuthException {

        //TODO VALIDATION
//        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
//        if (email != null) email = email.toLowerCase();
//        if (!pattern.matcher(email).matches())
//            throw new EtAuthException("Invalid email format");

        int phoneCnt = userRepository.countByPhone(phone);
        if (phoneCnt > 0)
            throw new EtAuthException(phone + " is already taken");

        if (!password.equals(confirm))
            throw new EtAuthException("Password & Confirm Password does not match.");

//        int emailCnt = userRepository.countByEmail(email);
//        if (emailCnt > 0)
//            throw new EtAuthException("email already in use");

        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));//TODO gen salt
        User user = userRepository.save(phone, name, hashPassword, UUID.randomUUID());//TODO UUID GENERATOR
        UUID restaurantId = restaurantRepository.createRest(user.getRestaurantId());

        if (!restaurantId.equals(user.getRestaurantId())) {
            //TODO throw exception
        }

        return user;
    }

//    @Override
//    public User registerUser(String name, String email, String username, String password) throws EtAuthException {
//        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
//        if (email != null) email = email.toLowerCase();
//        if (!pattern.matcher(email).matches())
//            throw new EtAuthException("Invalid email format");
//
//        int usernameCnt = userRepository.getCountByUsername(username);
//        if (usernameCnt > 0)
//            throw new EtAuthException(username + " is already taken");
//
//        int emailCnt = userRepository.getCountByEmail(email);
//        if (emailCnt > 0)
//            throw new EtAuthException("email already in use");
//
//        UUID userId = userRepository.create(name, email, username, password);
//        return userRepository.findById(userId);
//    }

//    @Override
//    public List<User> getUsers() {
//        log.info("Fetching all users");
//        return userRepository.findAll();
//    }

//    @Override
//    public User getUser(String username) {
//        log.info("Fetching user {}", username);
//        return userRepository.findByUsername(username);
//    }

//    @Override
//    public User saveUser(User user) {
//        log.info("Saving new user {} to the database", user.getName());
//        return userRepository.save(user);
//    }

//    @Override
//    public List<Role> getRoles() {
//        log.info("Fetching all roles");
//        return roleRepository.findAll();
//    }

//    @Override
//    public Role saveRole(Role role) {
//        log.info("Saving new role {} to the database", role.getName());
//        return roleRepository.save(role);
//    }

//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        log.info("Adding role {} to user {}", roleName, username);
//        User user = userRepository.findByUsername(username);
//        Role role = roleRepository.findByName(roleName);
//        user.setRole(role);
//    }
}
