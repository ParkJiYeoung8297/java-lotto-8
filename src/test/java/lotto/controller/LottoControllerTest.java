package lotto.controller;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoControllerTest extends NsTest{
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 구매금액_단순입출력_테스트() {
        assertSimpleTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "구입금액을 입력해 주세요."
                    );
                }
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

    @Override
    public void runMain() {
        View view = new View();
        LottoController lottoController = new LottoController(view);
        lottoController.doLotto();
    }
}