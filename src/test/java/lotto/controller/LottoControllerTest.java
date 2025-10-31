package lotto.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.exception.ErrorMessage;
import lotto.service.LottoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoControllerTest extends NsTest{
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "구입금액을 입력해 주세요.",
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "당첨 번호를 입력해 주세요."
//                            "보너스 번호를 입력해 주세요.",
//                            "당첨 통계",
//                            "---",
//                            "3개 일치 (5,000원) - 1개",
//                            "4개 일치 (50,000원) - 0개",
//                            "5개 일치 (1,500,000원) - 0개",
//                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
//                            "6개 일치 (2,000,000,000원) - 0개",
//                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 구입금액_공백_예외_테스트(String text) {
        assertSimpleTest(() -> {
            runException(text);
            assertThat(output()).contains(ErrorMessage.IS_NULL_ERROR.getMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"ㄴ", "s","."})
    void 구입금액_숫자아님_예외_테스트(String text) {
        assertSimpleTest(() -> {
            runException(text);
            assertThat(output()).contains(ErrorMessage.NOT_NUMBER_ERROR.getMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0","-100","999"})
    void 구입금액_최소금액미만_예외_테스트(String text) {
        assertSimpleTest(() -> {
            runException(text);
            assertThat(output()).contains(ErrorMessage.PRICE_NOT_IN_RANGE.getMessage());
        });
    }

    @Test
    void 구입금액_1000단위아님_예외_테스트() {
        assertSimpleTest(() -> {
            runException("12030");
            assertThat(output()).contains(ErrorMessage.INVALID_PRICE_UNIT.getMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 당첨로또_공백_예외_테스트(String text) {
        assertSimpleTest(() -> {
            runException("8000",text);
            assertThat(output()).contains(ErrorMessage.IS_NULL_ERROR.getMessage());
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,5,6", "1", "1,2"})
    void 당첨로또_길이_예외_테스트(String text) {
        assertSimpleTest(() -> {
            runException("8000",text);
            assertThat(output()).contains(ErrorMessage.IS_NULL_ERROR.getMessage());
        });
    }

    @Override
    public void runMain() {
        View view = new View();
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(view, lottoService);
        lottoController.doLotto();
    }
}