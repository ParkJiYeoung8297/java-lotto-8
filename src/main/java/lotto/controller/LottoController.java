package lotto.controller;

import lotto.exception.Validator;
import lotto.service.LottoService;
import lotto.service.ResultDto;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final View view;
    private final LottoService lottoService;
    private static final String splitDelimeter=",";

    public LottoController(View view, LottoService lottoService){
        this.view = view;
        this.lottoService = lottoService;
    }

    public void doLotto(){
        purchaseLotto();
        List<Integer> targetLotto = inputTargetLotto();
        Integer bonusNumber = inputBonusNumber();
        if (targetLotto != null && bonusNumber != null){
            ResultDto resultDto=lottoService.generateResult(targetLotto, bonusNumber);
            view.printResult(resultDto);
        }
    }

    public void purchaseLotto(){
        Integer price = null;
        int count = 1;
        while (price == null && count <= 5){
            price = getPurchasePrice();
            count++;
        }
        if (price != null){
            view.printRandomLottos(lottoService.generateRandomLottos(price));
        }
    }

    public List<Integer> inputTargetLotto(){
        List<Integer> numbers = null;
        int count = 1;
        while (numbers == null && count <= 5){
            numbers = getTargetLotto();
            count++;
        }
        if (numbers != null){
            return numbers;
        }
        return null;
    }

    public Integer inputBonusNumber(){
        Integer number = null;
        int count = 1;
        while (number == null && count <= 5){
            number = getBonusNumber();
            count++;
        }
        if (number != null){
            return number;
        }
        return null;
    }

    private Integer getPurchasePrice(){
        try {
            return Integer.parseInt(view.requestPurchasePrice());
        }catch(IllegalArgumentException e)
        {
            view.printMessage(e.getMessage());
            return null;
        }
    }

    private List<Integer> getTargetLotto(){
        try {
            List<String> numbers = List.of(view.requestTargetLotto().split(splitDelimeter));
            Validator.validateTargetLottoLength(numbers);
            return changeToInt(numbers);
        }catch(IllegalArgumentException e)
        {
            view.printMessage(e.getMessage());
            return null;
        }
    }

    private Integer getBonusNumber(){
        try {
            return Integer.parseInt(view.requestBonusNumber());
        }catch(IllegalArgumentException e)
        {
            view.printMessage(e.getMessage());
            return null;
        }
    }

    private List<Integer> changeToInt(List<String> numbers){
        List<Integer> nums = new ArrayList<>();
        for (String number : numbers){
            nums.add(Validator.validateIntegerValue(number));
        }
        return nums;
    }





}
