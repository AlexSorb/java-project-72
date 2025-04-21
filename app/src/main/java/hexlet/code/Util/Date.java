package hexlet.code.Util;

public class Date {

    public static String  getBDName() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL", "jdbc:h2:mem:project");

    }

    public static String getUserName() {
        return System.getenv().getOrDefault("user", "");
    }

    public static String getPassword() {
        return System.getenv().getOrDefault("password", "");
    }

}
