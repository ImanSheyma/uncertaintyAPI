package com.api.uncertainty;

import com.api.uncertainty.RTools.SeasonalAdjustment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UncertaintyApplication {

	public static void main(String[] args) {
		SeasonalAdjustment a = new SeasonalAdjustment();
		SpringApplication.run(UncertaintyApplication.class, args);
	}

}
