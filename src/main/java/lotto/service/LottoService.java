package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Record;
import lotto.util.Constant;
import lotto.util.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    private final List<Lotto> randomLottos;

    public LottoService() {
        this.randomLottos = new ArrayList<>();
    }

    public LottoDto generateRandomLottos(int price) {
        int amount = calculateLottoCount(price);
        addRandomLottos(amount);

        List<List<Integer>> lottoNumbers = randomLottos.stream()
                .map(Lotto::getNumbers)
                .toList();
        return new LottoDto(lottoNumbers, amount);
    }

    public ResultDto generateResult(List<Integer> targetLotto, Integer bonusNumber) {
        Record record = Record.buildRecord();
        record.calculateWinning(targetLotto, bonusNumber, randomLottos);
        return new ResultDto(record.getPrizeRecord(), record.calculateWinningRate());
    }

    private void addRandomLottos(int amount){
        for (int i = 1; i <= amount; i++) {
            Lotto lotto = makeLotto();
            randomLottos.add(lotto);
        }
    }

    private Lotto makeLotto(){
        return Lotto.buildLotto(RandomGenerator.getRandomLottoNumber());
    }

    private int calculateLottoCount(int price) {
        return price / Constant.MONEY_UNIT;
    }
}
