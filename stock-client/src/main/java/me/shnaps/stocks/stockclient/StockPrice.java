package me.shnaps.stocks.stockclient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record StockPrice(String symbol, double price, LocalDateTime time) {
}
