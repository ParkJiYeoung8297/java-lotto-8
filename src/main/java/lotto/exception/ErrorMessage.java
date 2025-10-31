package lotto.exception;

public enum ErrorMessage {
    IS_NULL_ERROR("[ERROR] 값이 비어있습니다."),
    LOTTO_NUMBER_NOT_IN_RANGE("[ERROR] 로또 번호는 1-45 사이 숫자여야합니다."),
    NOT_UNIQUE_LOTTO_NUMBER("[ERROR] 로또 번호는 중복될 수 없습니다."),
    NOT_UNIQUE_BONUS_NUMBER("[ERROR] 보너스 번호는 로또 번호와 중복될 수 없습니다."),
    PRICE_NOT_IN_RANGE("[ERROR] 최소 구입 금액은 1,000입니다."),
    INVALID_PRICE_UNIT("[ERROR] 구입금액은 1,000 단위여야 합니다."),
    NOT_NUMBER_ERROR("[ERROR] 값은 숫자여야합니다."),
    LOTTO_NUMBER_LENGTH_ERROR("[ERROR] 로또 번호는 6개여야 합니다.");

    private final String message;

    ErrorMessage(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }

}
