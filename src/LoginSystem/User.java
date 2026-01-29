package LoginSystem;

public class User {
    /*
    User的Javabean类
    属性： 用户名，密码，身份证号码，手机号码
     */
    private String name;
    private String passcode;
    private String IDCode;
    private String phonenumber;

    public User() {
    }

    public User(String name,String passcode,String IDCode,String phonenumber) {
        this.name = name;
        this.passcode = passcode;
        this.IDCode = IDCode;
        this.phonenumber = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getIDCode() {
        return IDCode;
    }

    public void setIDCode(String IDCode) {
        this.IDCode = IDCode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
