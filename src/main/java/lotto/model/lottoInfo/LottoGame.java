package lotto.model.lottoInfo;

public class LottoGame {
    private WinningNumber winningNumbers;
    private BonusNumber bonusNumber;
    private final LottoPrice price;
    private final PriceData priceByRank;

    public LottoGame(LottoPrice price, PriceData priceByRank) {
        this.price = price;
        this.priceByRank = priceByRank;
    }

    public Integer getPrice() {
        return price.getPrice();
    }
}
