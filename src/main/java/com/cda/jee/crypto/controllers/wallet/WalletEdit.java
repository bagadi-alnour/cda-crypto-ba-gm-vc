package com.cda.jee.crypto.controllers.wallet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cda.jee.crypto.dao.impl.CryptoCurrencyImpl;
import com.cda.jee.crypto.dao.impl.CryptoWalletImpl;
import com.cda.jee.crypto.model.CryptoCurrency;
import com.cda.jee.crypto.model.CryptoWallet;

@WebServlet("/wallet-edit")
public class WalletEdit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoWalletImpl cryptoWalletsImpl = new CryptoWalletImpl();
		if (cryptoWalletsImpl.get(Integer.parseInt(req.getParameter("id"))).isPresent()) {
			Optional<CryptoWallet> cryptoWallet = cryptoWalletsImpl.get(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("cryptoWallet", cryptoWallet.get());
			req.setAttribute("isFound", true);
		} else {
			req.setAttribute("cryptoWallet", null);
			req.setAttribute("isFound", false);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/wallets/wallet-edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoWalletImpl cryptoWalletsImpl = new CryptoWalletImpl();
		if (req.getParameter("id") != null && cryptoWalletsImpl.get(Integer.parseInt(req.getParameter("id"))).isPresent()) {
			Optional<CryptoWallet> cryptoWallet = cryptoWalletsImpl.get(Integer.parseInt(req.getParameter("id")));
			CryptoWallet myCryptoWallet = cryptoWallet.get();
			List<String> params = new ArrayList<String>(req.getParameterMap().keySet());
			for (String param : params) {
				if (!req.getParameter(param).isEmpty() && !req.getParameter(param).isBlank()) {
					switch (param) {
					case "purchasePrice":
						myCryptoWallet.setPurchasePrice(Double.parseDouble(req.getParameter(param)));						
						break;
					case "quantity":
						myCryptoWallet.setQuantity(Double.parseDouble(req.getParameter(param)));						
						break;
					case "purchaseDate":
						myCryptoWallet.setPurchaseDate(LocalDateTime.parse(req.getParameter(param)));						
						break;				
					default:
						break;
					}
				}
			}
			cryptoWalletsImpl.update(myCryptoWallet, myCryptoWallet.getIdWallet());
		}
		resp.sendRedirect(req.getContextPath() + "/wallets");		
	}
}
