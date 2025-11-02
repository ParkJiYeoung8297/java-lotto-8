package lotto.domain;

public enum Lottery {
    NOT_PRIZE("2개 이하 일치(상금 없음", 0),
    LOTTERY3("3개 일치 (5,000원)", 5000),
    LOTTERY4("4개 일치 (50,000원)", 50000),
    LOTTERY5("5개 일치 (1,500,000원)", 1500000),
    LOTTERY5b("5개 일치, 보너스 볼 일치 (30,000,000원)", 30000000),
    LOTTERY6("6개 일치 (2,000,000,000원)", 200000000);

    private final String message;
    private final Integer prize;

    Lottery(String message, Integer prize) {
        this.message = message;
        this.prize = prize;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPrize() {
        return prize;
    }
}
