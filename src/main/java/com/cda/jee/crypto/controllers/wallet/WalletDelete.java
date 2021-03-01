package com.cda.jee.crypto.controllers.wallet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.dao.impl.CryptoWalletImpl;
import com.cda.jee.crypto.model.CryptoWallet;

@WebServlet("/wallet-delete")
public class WalletDelete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		 
		resp.setContentType("text/html");
		CryptoWalletImpl cryptowalleImpl = new CryptoWalletImpl();
		if (cryptowalleImpl.get(Integer.parseInt(req.getParameter("id"))).isPresent()) {
			Optional<CryptoWallet> cryptoWallet = cryptowalleImpl.get(Integer.parseInt(req.getParameter("id")));
			CryptoWallet myWallet = cryptoWallet.get();
			cryptowalleImpl.delete(myWallet.getIdWallet());			
			resp.sendRedirect(req.getContextPath() + "/cryptocurrencies");	
		}
	}
}
