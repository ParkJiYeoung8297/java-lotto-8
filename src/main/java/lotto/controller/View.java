package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorMessage;
import lotto.exception.Validator;

import java.util.NoSuchElementException;

public class View {

    public String requestPurchasePrice(){
        printMessage(Message.REQUEST_PURCHASE_PRICE);
        String message = getNotNullMessage();
        Validator.validatePurchasePrice(message);
        return message;
    }

    private String getInputMessage(){
        return Console.readLine();
    }

    protected void printMessage(String message){
        System.out.println(message);
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
