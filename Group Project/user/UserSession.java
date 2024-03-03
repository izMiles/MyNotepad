package project.finals.user;


public class UserSession {
    private static String userName;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String name) {
        userName = name;
    }
}