package com.company.app.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exchange {

    private String stockExchange;
    private AliExpressExchange aliExpressExchange;
    private String centralBankExchange;

    @Override
    public String toString() {
        return stockExchange + "/" + aliExpressExchange + "/" + centralBankExchange;
    }
}
