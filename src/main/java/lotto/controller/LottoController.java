package lotto.controller;

public class LottoController {

    private final View view;
    private static final String splitDelimeter=",";

    public LottoController(View view){
        this.view = view;
    }

    public void doLotto(){
        purchaseLotto();
    }

    public void purchaseLotto(){
        Integer price = null;
        int count = 1;
        while (price == null && count <= 5){
            price = getPurchasePrice();
            count++;
        }

    }

    public Integer getPurchasePrice(){
        try {
            return Integer.parseInt(view.requestPurchasePrice());
        }catch(IllegalArgumentException e)
        {
            view.printMessage(e.getMessage());
            return null;
        }
    }


}
