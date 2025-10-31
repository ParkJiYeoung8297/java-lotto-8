package lotto.controller;

import lotto.service.LottoService;

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
        inputTargetLotto();
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

    public void inputTargetLotto(){
        String numbers = null;
        int count = 1;
        while (numbers == null && count <= 5){
            numbers = getTargetLotto();
            count++;
        }
        if (numbers != null){
        }
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

    private String getTargetLotto(){
        try {
            return view.requestTargetLotto();
        }catch(IllegalArgumentException e)
        {
            view.printMessage(e.getMessage());
            return null;
        }
    }




}
