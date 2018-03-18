package web.utilities.exceptions;

/**
 * Created by 61990 on 2018/3/11.
 */
public class UserNotExistException extends Exception {

    @Override
    public String getMessage() {
        return "用户账号不存在！";
    }
}
