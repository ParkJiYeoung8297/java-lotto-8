package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @Test
    void 로또_객체생성_테스트() {
        assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또_번호의_개수가_6개가_넘어가면_예외가_발생한다")
    void 로또_번호_개수초과_예외_테스트() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호_중복_예외_테스트() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1-45 사이 숫자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("outBoundLottoNumbersProvider")
    void 로또_번호_범위밖_예외_테스트(List<Integer> numbers) {
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class);
    }
    static Stream<List<Integer>> outBoundLottoNumbersProvider() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 78),
                List.of(0, 2, 3, 4, 5, 6),
                List.of(1, 2, 3, 4, 5, 46)
        );
    }

    @DisplayName("로또 번호는 오름차순 정렬되야한다.")
    @Test
    void 로또_정렬_테스트() {
        Lotto lotto = new Lotto(List.of(1, 2, 32, 4, 3, 5));
        List<Integer> expected = lotto.getNumbers();
        List<Integer> actual = List.of(1, 2, 3, 4, 5, 32);

        assertThat(expected).containsExactlyElementsOf(actual);
    }

}
