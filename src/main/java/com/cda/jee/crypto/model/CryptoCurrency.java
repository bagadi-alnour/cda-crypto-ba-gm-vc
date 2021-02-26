package com.cda.jee.crypto.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CryptoCurrency {
	private int idCrypto;
	private String name;
	private String symbol;
	private double currentPrice;
	private String imageUrl;
	private LocalDateTime lastUpdated;
}