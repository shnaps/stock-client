package me.shnaps.stocks.stockui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import me.shnaps.stocks.stockclient.StockClient;
import me.shnaps.stocks.stockclient.StockPrice;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static java.lang.String.valueOf;
import static javafx.collections.FXCollections.observableArrayList;

@Component
public class ChartController {
	@FXML
	public LineChart<String, Double> chart;
	private StockClient stockClient;


	public ChartController(StockClient stockClient) {
		this.stockClient = stockClient;
	}

	public void initialize() {
		PriceSubscriber priceSubscriber1 = new PriceSubscriber("SYMBOL", stockClient);
		PriceSubscriber priceSubscriber2 = new PriceSubscriber("AMZN", stockClient);


		ObservableList<Series<String, Double>> data = observableArrayList();
		data.add(priceSubscriber1.getSeries());
		data.add(priceSubscriber2.getSeries());
		chart.setData(data);
	}


	private class PriceSubscriber implements Consumer<StockPrice> {
		private Series<String, Double> series;
		private ObservableList<Data<String, Double>> seriesData = observableArrayList();

		public PriceSubscriber(String symbol, StockClient stockClient) {
			series = new Series<>(symbol, seriesData);
			stockClient.pricesFor(symbol).subscribe(this);
		}

		@Override
		public void accept(StockPrice stockPrice) {
			Platform.runLater(() -> seriesData.add(new Data<>(valueOf(stockPrice.time().getSecond()), stockPrice.price())));
		}

		public Series<String, Double> getSeries() {
			return series;
		}
	}
}
