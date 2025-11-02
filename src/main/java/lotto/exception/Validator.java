package lotto.exception;

import lotto.util.Constant;
import java.util.List;

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


    public static void validateTargetLottoLength(List<String> numbers) {
        if (numbers.size() != Constant.LOTTO_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.LOTTO_NUMBER_LENGTH_ERROR.getMessage());
        }
    }

}
