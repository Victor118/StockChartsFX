package com.zoicapital.stockchartsfx;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.util.StringConverter;

public class DateAxisFormatter extends StringConverter<Number>{

	protected Series<Number, Number> series;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
	
	public  DateAxisFormatter(Series<Number, Number> series) {
		this.series = series;
	}
	
	@SuppressWarnings("restriction")
	@Override
	public Number fromString(String d) {
		 for (Iterator<Data<Number,Number>> iter = series.getData().iterator();iter.hasNext();) {
			Data<Number,Number> data = iter.next();
			BarData bar = (BarData)data.getExtraValue();
			String dateStr = sdf.format(bar.getDateTime().getTime());
			if(dateStr.equals(d)){
				return bar.getIndex();
			}
		}
		 return null;
	}

	@Override
	public String toString(Number index) {
		if(index == null || index.intValue() >= series.getData().size()){
			return "";
		}else{
			BarData bar = (BarData)series.getData().get(index.intValue()).getExtraValue();
			return sdf.format(bar.getDateTime().getTime());
		}
	}
	

}
