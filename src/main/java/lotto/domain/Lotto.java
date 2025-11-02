package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // 숫자 비교
    public int compareLotto(List<Integer> num){
        Set<Integer> targetLotto = new HashSet<>(num);
        Set<Integer> randomLotto = new HashSet<>(numbers);
        targetLotto.retainAll(randomLotto);
        return targetLotto.size();
    }

    // 보너스 번호 맞춤 여부
    public int checkBonusNumber(Integer bonusNum){
        if (numbers.contains(bonusNum) == true){
            return 1;
        }
        return 0;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
