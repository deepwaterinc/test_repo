import com.education.Utils.QuestionUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для класса QuestionUtil.
 *
 * @author Fedor Shestakov
 */

public class QuestionUtilTest {

    /**
     * Тест для метода textTransformer.
     *
     */

    @ParameterizedTest
    @CsvSource({"Gtxtymrf, Печенька", "Ljujdjh №325, Договор №325", "Договор №11, Договор №11"})
    public void textShouldBeTransformed(String input, String expectedOutput) {

        String actualOutput = QuestionUtil.getInstance().textTransformer(input);
        assertEquals(expectedOutput, actualOutput);
    }

}
