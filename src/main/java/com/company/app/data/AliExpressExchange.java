package com.company.app.data;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AliExpressExchange {

    private String exchange;
    private Price rubPrice;
    private Price usdPrice;

    public AliExpressExchange(Price rubPrice, Price usdPrice) {
        this.rubPrice = rubPrice;
        this.usdPrice = usdPrice;
        this.exchange = String.valueOf(Integer.parseInt(rubPrice.getPrice()) / Integer.parseInt(usdPrice.getPrice()));
    }

    @Override
    public String toString() {
        return exchange;
    }
}
