package me.shnaps.stocks.stockclient;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

	@Bean
	@Profile("sse")
	public StockClient webClientStockClient(WebClient webClient) {
		return new WebClientStockClient(webClient);
	}

	@Bean
	@Profile("rsocket")
	public StockClient rSocketStockClient(RSocketRequester rSocketRequester) {
		return new RSocketStockClient(rSocketRequester);
	}

	@Bean
	@ConditionalOnMissingBean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	@Bean
	public RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
		return builder.tcp("localhost", 7000);
	}
}
