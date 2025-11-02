package lotto.domain;

import lotto.util.Constant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Winning {
    private final List<Lotto> randomLottos;
    private Map<Lottery, Integer> records;
    private int total;

    public Winning(List<Lotto> randomLottos) {
        this.randomLottos = randomLottos;
        this.records = new LinkedHashMap<>();
        for (Lottery lottery : Lottery.values()) {
            this.records.put(lottery, 0);
        }
        this.total = 0;
    }

    // record 갱신
    private void updateRecord(Lottery lottery) {
        records.replace(lottery, records.get(lottery) + 1);
    }

    // record 갱신
    private void updateTotal(int amount) {
        total += amount;
    }

    // 랜덤 로또들 당첨 계산
    public void calculateWinning(List<Integer> nums, Integer bonusNum) {
        for (Lotto lotto : randomLottos) {
            boolean isbonus = lotto.checkBonusNumber(bonusNum);
            int count = lotto.compareLotto(nums);

            Lottery lottery = getPrizeType(count, isbonus);

            updateRecord(lottery);
            updateTotal(lottery.getPrize());
        }
    }

    private Lottery getPrizeType(int count, boolean isbonus) {
        if (count < 3) {
            return Lottery.NOT_PRIZE;
        }
        String message = "LOTTERY" + count;
        if (isbonus && count == 5) {
            message += "b";
        }
        Lottery type = Lottery.valueOf(message);
        return type;
    }

    // record 반환
    public Map<String, Integer> getRecord() {
        Map<String, Integer> finalRecord = new LinkedHashMap<>();
        for (Lottery lottery : records.keySet()) {
            if (lottery == Lottery.NOT_PRIZE) {
                continue;
            }
            finalRecord.put(lottery.getMessage(), records.get(lottery));
        }
        return finalRecord;

    }

    // 수익률 계산
    public String calculateWinningRate() {
        int money = 0;
        for (int record : records.values()) {
            money += record * Constant.MONEY_UNIT;
        }
        return String.format("%.1f", (((float) total / money) * 100));
    }
}

