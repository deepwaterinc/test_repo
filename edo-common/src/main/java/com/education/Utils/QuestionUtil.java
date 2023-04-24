package com.education.Utils;

import lombok.*;

/**
 * Утилитный класс для поиска.
 *
 * @author Fedor Shestakov
 * @version 1.0
 * @since 1.0
 */

@NoArgsConstructor
public class QuestionUtil {

    /**
     * Утилитный метод
     * Реализует трансформацию текста на русский язык при неправильной раскладке
     */
    public static String textTransformer(String someText) {
        String eng = "qwertyuiop[]asdfghjkl;'zxcvbnm,.";
        String rus = "йцукенгшщзхъфывапролджэячсмитьбю";
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
