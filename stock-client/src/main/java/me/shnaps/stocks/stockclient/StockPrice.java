package me.shnaps.stocks.stockclient;

import java.time.LocalDateTime;

public class StockPrice {
	private String symbol;
	private Double price;
	private LocalDateTime time;

	public StockPrice() {
	}

	public StockPrice(String symbol, Double price, LocalDateTime time) {
		this.symbol = symbol;
		this.price = price;
		this.time = time;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}
