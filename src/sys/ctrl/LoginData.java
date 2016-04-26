package sys.ctrl;
/**
 * @author CK Seong
 */

//login data with class variables and static methods, will pass to main ui
public class LoginData {

    private static String login_UNAME;
    private static String login_FNAME;
    private static String login_ROLE;
    private static String login_EMPID;

    public static void setLogin_ROLE(String login_ROLE) {
        LoginData.login_ROLE = login_ROLE;
    }

    public static void setLogin_UNAME(String login_UNAME) {
        LoginData.login_UNAME = login_UNAME;
    }

    public static void setLogin_FNAME(String login_FNAME) {
        LoginData.login_FNAME = login_FNAME;
    }

    public static void setLogin_EMPID(String login_EMPID) {
        LoginData.login_EMPID = login_EMPID;
    }

    public static String getLogin_EMPID() {
        return login_EMPID;
    }

    public static String getLogin_FNAME() {
        return login_FNAME;
    }

    public static String getLogin_ROLE() {
        return login_ROLE;
    }

    public static String getLogin_UNAME() {
        return login_UNAME;
    }
}
