import exceptions.WrongConfirmPasswordException;
import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(checkWrapper(
                "java_skypro.go",
                "D_1hWiKjjP_9",
                "D_1hWiKjjP_9"
        ));
    }

    public static boolean check(String login, String password, String confirmPassword) {
        //Используется regExp: латинские буквы, цифры, "_", ".". Количество символов от 1 до 20 включительно
        if (!(Pattern.matches("^[a-zA-Z._0-9]{1,20}$", login))) {
            throw new WrongLoginException();
        }
        //Используется regExp: латинские буквы, цифры, "_". Количество символов от 1 до 19 включительно
        if (!(Pattern.matches("^[a-zA-Z_0-9]{1,19}$", password))) {
            throw new WrongPasswordException();
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongConfirmPasswordException();
        }

        return true;
    }
    //Обертка для метода check. Служит для обработки ошибок, если "ловится" ошибка, возвращается false
    public static boolean checkWrapper(String login, String password, String confirmPassword) {
        try {
            return check(login, password, confirmPassword);
        } catch (WrongLoginException | WrongPasswordException | WrongConfirmPasswordException e) {
            return false;
        }
    }

}