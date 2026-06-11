package util;

public interface Acceptable {
    String DEV_ID_VALID = "^DEV\\d{3}$";

    String FULL_NAME_VALID = "";
    String PROJECT_ID_VALID = "";

    public static boolean isValid(String data, String regex) {

        return (data != null && data.matches(regex));
    }
}
