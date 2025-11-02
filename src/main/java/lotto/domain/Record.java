package lotto.domain;

import lotto.util.Constant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Record {
    private Map<Lottery, Integer> prizeRecords;
    private int total;

    private Record() {
        this.prizeRecords = new LinkedHashMap<>();
        this.total = 0;
    }

    public static Record buildRecord() {
        Record record = new Record();
        for (Lottery lottery : Lottery.values()) {
            record.prizeRecords.put(lottery, 0);
        }
        return record;
    }

    public void calculateWinning(List<Integer> nums, Integer bonusNum, List<Lotto> randomLottos) {
        for (Lotto lotto : randomLottos) {
            boolean isbonus = lotto.checkBonusNumber(bonusNum);
            int count = lotto.compareLotto(nums);
            updateRecord(count, isbonus);
        }
    }

    public void updateRecord(int count, boolean isbonus) {
        Lottery lottery = getPrizeType(count, isbonus);
        updatePrizeRecords(lottery);
        updateTotal(lottery.getPrize());
    }

    private void updatePrizeRecords(Lottery lottery) {
        prizeRecords.replace(lottery, prizeRecords.get(lottery) + 1);
    }

    private void updateTotal(int amount) {
        total += amount;
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

    public Map<String, Integer> getPrizeRecord() {
        Map<String, Integer> finalRecord = new LinkedHashMap<>();
        for (Lottery lottery : prizeRecords.keySet()) {
            if (lottery == Lottery.NOT_PRIZE) {
                continue;
            }
            finalRecord.put(lottery.getMessage(), prizeRecords.get(lottery));
        }
        return finalRecord;
    }

    public String calculateWinningRate() {
        int money = 0;
        for (int record : prizeRecords.values()) {
            money += record * Constant.MONEY_UNIT;
        }
        return String.format("%.1f", (((float) total / money) * 100));
    }


}
