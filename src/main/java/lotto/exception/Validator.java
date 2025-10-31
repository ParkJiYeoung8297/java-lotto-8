package lotto.exception;

public final class Validator {
    private Validator(){}

    public static void validatePurchasePrice(String message){
        Integer value = validateIntegerValue(message);
        validateOverMinimumMoney(value);
        validateThousandUnitMoney(value);
    }


    public static void validateNotEmptyValue(String message){
        if (message==null || message.isBlank()){
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_ERROR.getMessage());
        }
    }

    public static Integer validateIntegerValue(String message){
        try{
            return Integer.parseInt(message);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER_ERROR.getMessage());
        }
    }

    public static void validateOverMinimumMoney(Integer money){
        if (money < 1000){
            throw new IllegalArgumentException(ErrorMessage.PRICE_NOT_IN_RANGE.getMessage());
        }
    }

    public static void validateThousandUnitMoney(Integer money){
        if (money % 1000 != 0){
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRICE_UNIT.getMessage());
        }
    }
    
}
