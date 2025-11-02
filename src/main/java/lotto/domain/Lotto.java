package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.Validator;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateLottoLength(numbers);
        validateUniqueNumber(numbers);
        validateNumberInRange(numbers);
        this.numbers = new ArrayList<>(numbers);
        Collections.sort(this.numbers);
    }

    private void validateLottoLength(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_LENGTH_ERROR.getMessage());
        }
    }

    private void validateUniqueNumber(List<Integer> numbers)  {
        Set<Integer> nums = new HashSet<>(numbers);
        if (nums.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.NOT_UNIQUE_LOTTO_NUMBER.getMessage());
        }
    }

    private void validateNumberInRange(List<Integer> numbers)  {
        for (int num : numbers){
            Validator.validateNotEmptyValue(Integer.toString(num));
            if (num < 1 || num > 45){
                throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_NOT_IN_RANGE.getMessage());
            }
        }
    }



    // 숫자 비교
    public int compareLotto(List<Integer> num) {
        Set<Integer> targetLotto = new HashSet<>(num);
        Set<Integer> randomLotto = new HashSet<>(numbers);
        targetLotto.retainAll(randomLotto);
        return targetLotto.size();
    }

    // 보너스 번호 맞춤 여부
    public int checkBonusNumber(Integer bonusNum) {
        if (numbers.contains(bonusNum) == true) {
            return 1;
        }
        return 0;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

}
