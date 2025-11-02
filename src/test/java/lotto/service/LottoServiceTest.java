package lotto.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    void 로또_결과_반환_테스트() {
        LottoService lottoService = new LottoService();
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    lottoService.generateRandomLottos(8000);
                    ResultDto result = lottoService.generateResult(List.of(1, 2, 3, 4, 5, 6), 7);
                    Map<String, Integer> expected = new LinkedHashMap<>();
                    expected.put("3개 일치 (5,000원)", 1);
                    expected.put("4개 일치 (50,000원)", 0);
                    expected.put("5개 일치 (1,500,000원)", 0);
                    expected.put("5개 일치, 보너스 볼 일치 (30,000,000원)", 0);
                    expected.put("6개 일치 (2,000,000,000원)", 0);
                    expected.put("3개 일치 (5,000원)", 1);

                    assertThat(result.getWinningRecord()).isEqualTo(expected);
                    Assertions.assertEquals("62.5", result.getReturnRate());
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

}