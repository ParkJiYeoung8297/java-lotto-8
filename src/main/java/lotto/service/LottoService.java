package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Winning;
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
        for (int i = 1; i <= amount; i++) {
            Lotto lotto = new Lotto(RandomGenerator.getRandomLottoNumber());
            randomLottos.add(lotto);
        }

        // TODO : 이거 정렬해서 출력해야함!!!!
        List<List<Integer>> lottoNumbers = randomLottos.stream()
                .map(Lotto::getNumbers)
                .toList();
        return new LottoDto(lottoNumbers, amount);
    }

    public ResultDto generateResult(List<Integer> targetLotto, Integer bonusNumber) {
        Winning winning = new Winning(randomLottos);
        winning.calculateWinning(targetLotto, bonusNumber);
        return new ResultDto(winning.getRecord(), winning.calculateWinningRate());
    }

    private int calculateLottoCount(int price) {
        return price / 1000;
    }  // TODO: 이거 매직넘버해라
}
