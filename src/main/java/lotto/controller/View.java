package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorMessage;
import lotto.exception.Validator;
import lotto.service.LottoDto;

import java.util.List;
import java.util.NoSuchElementException;

public class View {

    public String requestPurchasePrice(){
        printMessage(Message.REQUEST_PURCHASE_PRICE);
        String message = getNotNullMessage();
        Validator.validatePurchasePrice(message);
        return message;
    }

    public String requestTargetLotto(){
        printMessage(Message.REQUEST_TARGET_LOTTO);
        String message = getNotNullMessage();
        return message;
    }

    public String requestBonusNumber(){
        printMessage(Message.REQUEST_BONUS_NUMBER);
        String message = getNotNullMessage();
        Validator.validateIntegerValue(message);
        return message;
    }

    public void printRandomLottos(LottoDto lottoDto){
        printMessage(String.format(Message.PRINT_LOTTO_COUNT, lottoDto.getCount()));
        for (List<Integer> lotto : lottoDto.getRandomLottos()){
            printIntegerList(lotto);
        }
    }

    private String getInputMessage(){
        return Console.readLine();
    }

    protected void printMessage(String message){
        System.out.println(message);
    }
    protected void printIntegerList(List<Integer> lotto){
        System.out.println(lotto);
    }

    private String getNotNullMessage(){
        try{
            String message = getInputMessage();
            Validator.validateNotEmptyValue(message);
            return message;
        } catch(NoSuchElementException e){
            throw new IllegalArgumentException(ErrorMessage.IS_NULL_ERROR.getMessage());
        }
    }


}
