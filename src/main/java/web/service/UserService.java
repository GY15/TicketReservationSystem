package web.service;

/**
 * 管理用户登录
 */
public interface UserService {
    boolean login(String userID, String password);
}
