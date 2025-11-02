package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class RandomGenerator {
    public static final int START_NUMBER = 1;
    public static final int END_NUMBER = 45;
    public static final int COUNT = 6;

    public static List<Integer> getRandomLottoNumber(){
        return Randoms.pickUniqueNumbersInRange(START_NUMBER, END_NUMBER, COUNT);
    }

}
