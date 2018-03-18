package web.utilities.exceptions;

/**
 * Created by 61990 on 2018/3/11.
 */
public class UserExistException extends Exception {

    @Override
    public String getMessage() {
        return "注册的用户已经存在！";
    }
}
