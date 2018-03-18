package web.utilities.exceptions;

/**
 * Created by 61990 on 2018/3/11.
 */
public class MemberInvalidExistException extends Exception {

    @Override
    public String getMessage() {
        return "会员账号已经被封杀！";
    }
}
