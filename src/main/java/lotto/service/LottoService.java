package lotto.service;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    public LottoDto generateRandomLottos(int price){
        List<List<Integer>> randomLottos=new ArrayList<>();
        int amount = calculateLottoCount(price);
        for (int i=1; i <= amount;i++){
            randomLottos.add(RandomGenerator.getRandomLottoNumber());
        }
        return new LottoDto(randomLottos, amount);
    }

    private int calculateLottoCount(int price){
        return price / 1000;
    }
}
