package lotto.controller;

import lotto.model.LottoPurchaseHistory;
import lotto.model.lottoInfo.LottoGame;
import lotto.model.lottoInfo.PriceDataImpl;
import lotto.model.lottoInfo.StandardLottoPrice;
import lotto.service.LottoService;
import lotto.ui.InputView;
import lotto.ui.OutputView;
import lotto.validator.MoneyValidator;

public class LottoController {
    private static final LottoService lottoService = new LottoService();
    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final MoneyValidator moneyValidator = new MoneyValidator();

    public void run() {
        LottoGame lottoGame = new LottoGame(new StandardLottoPrice(), new PriceDataImpl());
        int money = inputMoney(lottoGame.getPrice());
        LottoPurchaseHistory lottoPurchaseHistory = buyLotto(money, lottoGame.getPrice());
        printPurchaseHistory(lottoPurchaseHistory);
    }

    private int inputMoney(int lottoPrice) {
        while(true) {
            try{
                String money = inputView.inputMoney();
                moneyValidator.validateNumeric(money);

                moneyValidator.validateDivideWithLottoPrice(Integer.parseInt(money), lottoPrice);
                return Integer.parseInt(money);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private LottoPurchaseHistory buyLotto(int money, int lottoPrice) {
        int lottoAmount = money / lottoPrice;
        return lottoService.buyLotto(lottoAmount);
    }

    private void printPurchaseHistory(LottoPurchaseHistory lottoPurchaseHistory) {
        outputView.printPurchaseHistory(lottoPurchaseHistory.getPurchaseHistory());
    }
}
