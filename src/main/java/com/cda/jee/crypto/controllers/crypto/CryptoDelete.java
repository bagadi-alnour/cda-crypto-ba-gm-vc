package com.cda.jee.crypto.controllers.crypto;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.dao.impl.CryptoCurrencyImpl;
import com.cda.jee.crypto.model.CryptoCurrency;

@WebServlet("/cryptocurrency-delete")
public class CryptoDelete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		if (cryptoCurrenciesImpl.get(Integer.parseInt(req.getParameter("id"))).isPresent()) {
			Optional<CryptoCurrency> cryptoCurrency = cryptoCurrenciesImpl.get(Integer.parseInt(req.getParameter("id")));
			CryptoCurrency myCrypto = cryptoCurrency.get();
			cryptoCurrenciesImpl.delete(myCrypto.getIdCrypto());			
			resp.sendRedirect(req.getContextPath() + "/wallets");	
		}
	}
}
