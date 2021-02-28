package com.cda.jee.crypto.controllers.crypto;

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
import com.cda.jee.crypto.model.CryptoCurrency;

@WebServlet("/cryptocurrency-edit")
public class CryptoEdit extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		if (cryptoCurrenciesImpl.get(Integer.parseInt(req.getParameter("id"))).isPresent()) {
			Optional<CryptoCurrency> cryptoCurrency = cryptoCurrenciesImpl.get(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("cryptoCurrency", cryptoCurrency.get());
			req.setAttribute("isFound", true);
		} else {
			req.setAttribute("cryptoCurrency", null);
			req.setAttribute("isFound", false);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/cryptocurrencies/cryptocurrency-edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoCurrencyImpl cryptoCurrenciesImpl = new CryptoCurrencyImpl();
		if (req.getParameter("id") != null && cryptoCurrenciesImpl.get(Integer.parseInt(req.getParameter("id"))).isPresent()) {
			Optional<CryptoCurrency> cryptoCurrency = cryptoCurrenciesImpl.get(Integer.parseInt(req.getParameter("id")));
			CryptoCurrency myCrypto = cryptoCurrency.get();
			List<String> params = new ArrayList<String>(req.getParameterMap().keySet());
			for (String param : params) {
				if (!req.getParameter(param).isEmpty() && !req.getParameter(param).isBlank()) {
					switch (param) {
					case "name":
						myCrypto.setName(req.getParameter(param));						
						break;
					case "symbol":
						myCrypto.setSymbol(req.getParameter(param));						
						break;
					case "currentPrice":
						myCrypto.setCurrentPrice(Double.parseDouble(req.getParameter(param)));						
						break;
					case "imageUrl":
						myCrypto.setImageUrl(req.getParameter(param));						
						break;
					case "lastUpdated":
						myCrypto.setLastUpdated(LocalDateTime.parse(req.getParameter(param)));						
						break;
					default:
						break;
					}
				}
			}
			cryptoCurrenciesImpl.update(myCrypto, myCrypto.getIdCrypto());
		}
		resp.sendRedirect(req.getContextPath() + "/cryptocurrencies");		
	}
}
