package com.cda.jee.crypto.controllers.wallet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cda.jee.crypto.dao.impl.CryptoWalletImpl;
import com.cda.jee.crypto.model.CryptoWallet;

@WebServlet("/wallets")
public class WalletList extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		CryptoWalletImpl cryptoWalletImpl = new CryptoWalletImpl();
		List<CryptoWallet> cryptoWalletsList = cryptoWalletImpl.getAll();
		req.setAttribute("cryptoWalletsList", cryptoWalletsList);
		this.getServletContext().getRequestDispatcher("/WEB-INF/wallets/list.jsp").forward(req, resp);
	}
}