package web.utilities.enums;

public enum MemberState {
    REGISTERED("用户已经注册"),
    LOCKED("用户已经被锁定.不可用"),
    ALLOWED("用户验证码成功发送"),
    ERROR_MAIL("用户邮箱错误");

    private String repre;

    MemberState (String repre) {
        this.repre = repre;
    }

    /**
     *
     * @return 该枚举相对应的文件中形式
     *
     * enum TO String
     */
    public String getRepre() {
        return repre;
    }

    /**
     *
     * @return 该类型对应的枚举代码
     *
     * String TO enum
     * 便于从数据库读入
     */
    public static MemberState getEnum(String a) {
        for (MemberState thisEnum : MemberState.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
