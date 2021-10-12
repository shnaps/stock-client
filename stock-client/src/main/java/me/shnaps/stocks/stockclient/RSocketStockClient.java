package me.shnaps.stocks.stockclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

public class RSocketStockClient implements StockClient {
	private static Logger logger = LoggerFactory.getLogger(RSocketStockClient.class);

	private RSocketRequester rSocketRequester;

	public RSocketStockClient(RSocketRequester rSocketRequester) {
		this.rSocketRequester = rSocketRequester;
	}

	@Override
	public Flux<StockPrice> pricesFor(String symbol) {
		logger.info("RSocket stock client");
		return rSocketRequester.route("stockPrices")
				.data(symbol)
				.retrieveFlux(StockPrice.class)
				.retry(4)
				.doOnError(IOException.class, e -> logger.error(e.getMessage()));
	}

}
