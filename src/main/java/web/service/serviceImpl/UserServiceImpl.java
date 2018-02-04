package web.service.serviceImpl;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.dao.daoImpl.UserDaoImpl;
import web.model.User;
import web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    public boolean login(String userID, String password) {
        User user = userDao.getUser(userID);
        if (user == null||!user.getPassword().equals(password)) {
            return false;
        } else {
           return true;
        }
    }
}
