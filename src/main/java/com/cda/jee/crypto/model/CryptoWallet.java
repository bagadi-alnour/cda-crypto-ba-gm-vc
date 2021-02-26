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
public class CryptoWallet {
	private int idWallet;
	private int idCrypto;
	private double purchasePrice;
	private double quantity;
	private LocalDateTime purchaseDate;
}
