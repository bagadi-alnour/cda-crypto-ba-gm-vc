package com.cda.jee.crypto.controllers.crypto;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.dao.impl.CryptoCurrencyImpl;
import com.cda.jee.crypto.model.CryptoCurrency;

@WebServlet("/cryptocurrencies")
public class CryptoList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		List<CryptoCurrency> cryptoCurrenciesList = cryptoCurrenciesImpl.getAll();
		req.setAttribute("cryptoCurrenciesList", cryptoCurrenciesList);
		this.getServletContext().getRequestDispatcher("/WEB-INF/cryptocurrencies/list.jsp").forward(req, resp);
	}
}