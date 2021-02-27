package com.cda.jee.crypto.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.model.CryptoCurrency;

@WebServlet("/cryptocurrencies")
public class CryptoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		req.setAttribute("cryptoCurrenciesList", CryptoCurrency.getCryptoCurrenciesList());
        this.getServletContext().getRequestDispatcher("/WEB-INF/cryptocurrencies/list.jsp").forward(req, resp);
	}
}
