package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.User;
import team47pack.models.dto.UserInfo;
import team47pack.repository.UserRepo;

import java.util.List;

// @author: Lupus7 (Sinisa Canak)
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> findByAccepted(boolean accepted) {
        return userRepo.findByAccepted(accepted);
    }

    public boolean acceptUser(Long id) {
        // can be done with findById() and save(), but the current solution uses only 1 query
        return userRepo.acceptUser(id) != 0;
    }

    public boolean rejectUser(Long id) {
        // can be done with deleteById(), but I wanted a simple way to return info (is operation successful)
        return userRepo.rejectUser(id) != 0;
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public boolean save(UserInfo userInfo) {
        User u = userRepo.findByEmail(userInfo.getEmail());

        if (u == null)
            return false;

        u.setFirstName(userInfo.getFirstName());
        u.setLastName(userInfo.getLastName());
        u.setAddress(userInfo.getAddress());
        u.setCity(userInfo.getCity());
        u.setState(userInfo.getState());
        u.setTelephone(userInfo.getTelephone());
        u.setUniqueNum(userInfo.getUniqueNum());

        userRepo.save(u);

        return true;
    }
}
