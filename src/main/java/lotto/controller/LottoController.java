package lotto.controller;

public class LottoController {

    private final View view;
    private static final String splitDelimeter=",";

    public LottoController(View view){
        this.view=view;
    }

    public void doLotto(){
        purchaseLotto();
    }

    public void purchaseLotto(){
        Integer price = Integer.parseInt(view.requestPurchasePrice());
    }


}
