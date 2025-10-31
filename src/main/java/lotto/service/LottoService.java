package lotto.service;

import java.util.ArrayList;
import java.util.List;

public class LottoService {


    public List<List<Integer>> generateRandomLottos(int price){
        List<List<Integer>> randomLottos=new ArrayList<>();

        for (int i=1; i <= calculateLottoCount(price);i++){
            randomLottos.add(RandomGenerator.getRandomLottoNumber());
        }
        return randomLottos;
    }

    public int calculateLottoCount(int price){
        return price / 1000;
    }
}
