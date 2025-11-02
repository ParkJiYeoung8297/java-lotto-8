package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class RecordTest {
    @Test
    void 기록_객체생성_테스트() {
        assertThatCode(() -> Record.buildRecord())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("NOT_PRIZE를 제외한 등수별 당첨 내역이 반환된다.")
    void 기록_등수별_당첨내역_반환_기능_테스트() {
        // given
        Record record = Record.buildRecord();
        record.updateRecord(3, false); // LOTTERY3
        record.updateRecord(4, false); // LOTTERY4
        record.updateRecord(2, false); // NOT_PRIZE

        // when
        Map<String, Integer> result = record.getPrizeRecord();

        // then
        assertThat(result)
                .containsEntry(Lottery.LOTTERY3.getMessage(), 1)
                .containsEntry(Lottery.LOTTERY4.getMessage(), 1)
                .doesNotContainKey(Lottery.NOT_PRIZE.getMessage());
    }

    @Test
    @DisplayName("수익률이 소수점 첫째 자리까지 계산된다.")
    void 수익률_계산_기능_테스트() {
        // given
        Record record = Record.buildRecord();
        record.updateRecord(3, false); // 5,000원
        record.updateRecord(4, false); // 50,000원
        record.updateRecord(4, false); // 50,000원
        record.updateRecord(2, false); // 제외

        // when
        String rate = record.calculateWinningRate();

        // then
        // total = 105,000, money = 4개 × 1000
        // total / money × 100 = 105000 / 4000 × 100 = 2625.0
        assertThat(rate).isEqualTo("2625.0");
    }

}