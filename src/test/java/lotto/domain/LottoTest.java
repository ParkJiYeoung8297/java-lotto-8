package lotto.domain;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {

    @Test
    void 로또_객체생성_테스트() {
        assertThatCode(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1-45 사이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 로또_번호_범위밖_예외_테스트() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 78)))
                .isInstanceOf(IllegalArgumentException.class);
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
