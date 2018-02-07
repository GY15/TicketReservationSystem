package web.service;

import web.utilities.enums.MemberState;

/**
 * 管理用户登录
 */
public interface UserService {
    boolean login(String userID, String password);
    MemberState validEmail(String email);
}
