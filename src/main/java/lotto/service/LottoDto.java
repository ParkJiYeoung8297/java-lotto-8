package lotto.service;

import java.util.List;

public class LottoDto {
    private final List<List<Integer>> randomLottos;

    public LottoDto(List<List<Integer>> randomLottos){
        this.randomLottos=randomLottos;
    }

    public List<List<Integer>> getRandomLottos(){
        return randomLottos;
    }
}
