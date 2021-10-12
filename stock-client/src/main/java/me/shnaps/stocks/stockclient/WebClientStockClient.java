package me.shnaps.stocks.stockclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;


public class WebClientStockClient implements StockClient {
	private static Logger logger = LoggerFactory.getLogger(WebClientStockClient.class);

	private WebClient webClient;

	public WebClientStockClient(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public Flux<StockPrice> pricesFor(String symbol) {
		logger.info("WebClient stock client");
		return webClient.get()
				.uri("http://localhost:8080/stocks/{symbol}", symbol)
				.retrieve()
				.bodyToFlux(StockPrice.class)
				.retry(4)
				.doOnError(IOException.class, e -> logger.error(e.getMessage()));
	}
}
