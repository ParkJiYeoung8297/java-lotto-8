package lotto.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Winning {
    private final List<Lotto> randomLottos;
    private Map<Lottery,Integer> records;
    private int total;

    public Winning(List<Lotto> lottos){
        this.randomLottos = lottos;
        this.records = new LinkedHashMap<>(0);
        this.total = 0;
    }

    // record 갱신
    private void updateRecord(Lottery lottery){
        records.replace(lottery, records.get(lottery)+1);

    }

    // record 갱신
    private void updateTotal(int amount){
        total += amount;
    }

    // 랜덤 로또들 당첨 계산
    public void calculateWinning(List<Integer> nums, Integer bonusNum){
        for (Lotto lotto : randomLottos){
            int bonusCheck= lotto.checkBonusNumber(bonusNum);
            int count = lotto.compareLotto(nums) + bonusCheck;
            Lottery lottery = getPrizeType(count, bonusCheck);
            updateRecord(lottery);
            updateTotal(lottery.getPrize());
        }
    }

    public Map<Lottery, Integer> getRecords() {
        return records;
    }

    private Lottery getPrizeType(int count, int bonusCheck){
        if (count<3){
            return Lottery.NOT_PRIZE;
        }
        String message = "LOTTERY"+Integer.toString(count);
        if (bonusCheck==0 && count==5){
            message+="b";
        }
        Lottery type = Lottery.valueOf(message);
        return type;
    }



    // record 반환
    public Map<String,Integer> getRecord(){
        Map<String,Integer> finalRecord = new LinkedHashMap<>();
        for (Lottery lottery : records.keySet()){
            finalRecord.put(lottery.getMessage(), records.get(lottery));
        }
        return finalRecord;

    }

    // 수익률 계산
    public String calculateWinningRate(){
        float total = 0f;
        int count = 0;
        for (int record : records.values()){
            count += record;
        }
        return String.format("%.2f", (float)count / total);
    }
}

