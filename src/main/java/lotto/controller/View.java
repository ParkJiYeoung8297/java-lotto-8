package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.ErrorMessage;

public class View {

    public String requestPurchasePrice(){
        printMessage(Message.REQUEST_PURCHASE_PRICE);
        return getInputMessage();
    }


    private String getInputMessage(){
        return Console.readLine();
    }

    private void printMessage(String message){
        System.out.println(message);
    }



}
