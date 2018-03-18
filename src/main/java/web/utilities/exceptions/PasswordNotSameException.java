package web.utilities.exceptions;

/**
 * Created by 61990 on 2018/3/11.
 */
public class PasswordNotSameException extends Exception {

    @Override
    public String getMessage() {
        return "密码不一致！";
    }
}
