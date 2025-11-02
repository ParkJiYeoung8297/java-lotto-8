package lotto.domain;

import lotto.exception.ErrorMessage;
import lotto.exception.Validator;
import lotto.util.Constant;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public static Lotto buildLotto(List<Integer> numbers) {
        validateLottoLength(numbers);
        validateUniqueNumber(numbers);
        validateNumberInRange(numbers);

        Lotto lotto = new Lotto(numbers);
        Collections.sort(lotto.numbers);
        return lotto;
    }

    private static void validateLottoLength(List<Integer> numbers) {
        if (numbers.size() != Constant.LOTTO_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_LENGTH_ERROR.getMessage());
        }
    }

    private static void validateUniqueNumber(List<Integer> numbers) {
        Set<Integer> nums = new HashSet<>(numbers);
        if (nums.size() != Constant.LOTTO_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.NOT_UNIQUE_LOTTO_NUMBER.getMessage());
        }
    }

    private static void validateNumberInRange(List<Integer> numbers) {
        for (int num : numbers) {
            Validator.validateNotEmptyValue(Integer.toString(num));
            if (num < Constant.MIN_VALUE || num > Constant.MAX_VALUE) {
                throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_NOT_IN_RANGE.getMessage());
            }
        }
    }

    public int compareLotto(List<Integer> num) {
        Set<Integer> targetLotto = new HashSet<>(num);
        Set<Integer> randomLotto = new HashSet<>(numbers);

        targetLotto.retainAll(randomLotto);
        return targetLotto.size();
    }

    public boolean checkBonusNumber(Integer bonusNum) {
        if (numbers.contains(bonusNum) == true) {
            return true;
        }
        return false;
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

}
