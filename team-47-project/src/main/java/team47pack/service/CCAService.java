package team47pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team47pack.models.User;
import team47pack.repository.UserRepo;

import java.util.List;

@Service
public class CCAService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getRegRequest() {
        return userRepo.findByAccepted(false);
    }

    public boolean acceptReq(String mail) {
        if(userRepo.acceptUser(mail) != 0)
            return true;
        return false;
    }

    public boolean rejectReq(String mail) {
        if(userRepo.acceptUser(mail) != 0)
            return true;
        return false;
    }
}