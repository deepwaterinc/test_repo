package com.education.Utils;

/**
 * Утилитный класс для поиска.
 *
 * @author Fedor Shestakov
 * @version 1.0
 * @since 1.0
 */
public class QuestionUtil {

    private static QuestionUtil instance;

    private QuestionUtil() {
    }

    public static QuestionUtil getInstance() {
        if (instance == null) {
            instance = new QuestionUtil();
        }
        return instance;
    }

    /**
     * Утилитный метод
     * Реализует трансформацию текста на русский язык при неправильной раскладке
     */
    public String textTransformer(String someText) {
        String eng = "qwertyuiop[]\\asdfghjkl;'zxcvbnm,./";
        String rus = "йцукенгшщзхъ\\фывапролджэячсмитьбю.";
        StringBuilder result = new StringBuilder();
        for (char c : someText.toCharArray()) {
            int index = eng.indexOf(Character.toLowerCase(c));
            if (index >= 0) {
                char rusChar = rus.charAt(index);
                if (Character.isUpperCase(c)) {
                    rusChar = Character.toUpperCase(rusChar);
                }
                result.append(rusChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}
