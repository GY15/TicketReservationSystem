package web.utilities.enums;

public enum UserType {
    MEMBER("会员"),
    VENUE("场馆"),
    MANAGER("经理");

    private String repre;

    UserType(String repre) {
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
    public static UserType getEnum(String a) {
        for (UserType thisEnum : UserType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
