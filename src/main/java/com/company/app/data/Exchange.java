package com.company.app.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Exchange {

    private String stockExchange;
    private String aliExpressExchange;
    private String centralBankExchange;
}
