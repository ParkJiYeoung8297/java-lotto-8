package lotto.service;

import java.util.List;

public class LottoDto {
    private final List<List<Integer>> randomLottos;
    private final Integer amount;

    public LottoDto(List<List<Integer>> randomLottos, Integer amount){
        this.randomLottos = randomLottos;
        this.amount = amount;
    }

    public List<List<Integer>> getRandomLottos(){
        return randomLottos;
    }

    public Integer getCount(){
        return amount;
    }
}
