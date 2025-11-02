package lotto.exception;

import lotto.util.Constant;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Validator {

    private Validator() {
    }

    public static void validatePurchasePrice(String message) {
        Integer value = validateIntegerValue(message);
        validateOverMinimumMoney(value);
        validateThousandUnitMoney(value);
    }

    public static void validateNotEmptyValue(String message) {
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_ERROR.getMessage());
        }
    }

    public static Integer validateIntegerValue(String message) {
        try {
            return Integer.parseInt(message);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER_ERROR.getMessage());
        }
    }

    public static void validateOverMinimumMoney(Integer money) {
        if (money < Constant.MONEY_UNIT) {
            throw new IllegalArgumentException(ErrorMessage.PRICE_NOT_IN_RANGE.getMessage());
        }
    }

    public static void validateThousandUnitMoney(Integer money) {
        if (money % Constant.MONEY_UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE_UNIT.getMessage());
        }
    }

    public static void validateLottoLength(List<Integer> numbers) {
        if (numbers.size() != Constant.LOTTO_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_LENGTH_ERROR.getMessage());
        }
    }

    public static void validateUniqueBonusNumber(List<Integer> numbers, Integer num) {
        if (numbers.contains(num)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_UNIQUE_BONUS_NUMBER.getMessage());
        }
    }

    public static void validateNumberInRange(Integer num)  {
        if (num < Constant.MIN_VALUE || num > Constant.MAX_VALUE){
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_NOT_IN_RANGE.getMessage());
        }
    }

    public static void validateNumbersAllInRange(List<Integer> numbers)  {
        for (int num : numbers){
            Validator.validateNotEmptyValue(Integer.toString(num));
            validateNumberInRange(num);
        }
    }

    public static void validateUniqueNumber(List<Integer> numbers)  {
        Set<Integer> nums = new HashSet<>(numbers);
        if (nums.size() != Constant.LOTTO_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.NOT_UNIQUE_LOTTO_NUMBER.getMessage());
        }
    }

}
