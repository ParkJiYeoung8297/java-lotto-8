package lotto;

import lotto.controller.LottoController;
import lotto.controller.View;
import lotto.service.LottoService;

public class Application {
    public static void main(String[] args) {
        View view = new View();
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(view, lottoService);
        lottoController.doLotto();
    }
}
