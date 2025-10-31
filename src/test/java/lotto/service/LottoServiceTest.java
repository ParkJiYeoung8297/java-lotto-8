package lotto.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {

    @Test
    void 구입한_개수만큼_로또랜덤생성_테스트() {
        LottoService lottoService = new LottoService();
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                LottoDto result = lottoService.generateRandomLottos(5000);
                List<List<Integer>> expected = new ArrayList<>();
                expected.add(List.of(8, 21, 23, 41, 42, 43));
                expected.add(List.of(3, 5, 11, 16, 32, 38));
                expected.add(List.of(7, 11, 16, 35, 36, 44));
                expected.add(List.of(1, 8, 11, 31, 41, 42));
                expected.add(List.of(13, 14, 16, 38, 42, 45));
                assertThat(result.getRandomLottos()).containsExactlyElementsOf(expected);
                Assertions.assertEquals(5, result.getCount());
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45)
        );
    }

}