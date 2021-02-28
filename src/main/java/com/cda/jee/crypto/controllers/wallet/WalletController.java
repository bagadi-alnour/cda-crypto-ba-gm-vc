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
public class WalletController extends HttpServlet {

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

//List<CryptoWallet> cryptoWalletsList = new ArrayList<>();
//CryptoWallet wallet = new CryptoWallet(1, 2, 03.12, 3, LocalDateTime.of(2017, 2, 13, 15, 56));
//CryptoWallet wallet02 = new CryptoWallet(2, 3, 68.18, 0.1, LocalDateTime.of(2017, 2, 13, 15, 56));
//CryptoWallet wallet03 = new CryptoWallet(3, 4, 687.46, 1.21, LocalDateTime.of(2017, 2, 13, 15, 56));
//CryptoWallet wallet04 = new CryptoWallet(4, 5, 93.54, 2, LocalDateTime.of(2017, 2, 13, 15, 56));
//CryptoWallet wallet05 = new CryptoWallet(5, 6, 20.89, 0.25, LocalDateTime.of(2017, 2, 13, 15, 56));
//CryptoWallet wallet06 = new CryptoWallet(6, 1, 00.03, 0.78, LocalDateTime.of(2017, 2, 13, 15, 56));
//cryptoWalletsList.add(wallet);
//cryptoWalletsList.add(wallet02);
//cryptoWalletsList.add(wallet03);
//cryptoWalletsList.add(wallet04);
//cryptoWalletsList.add(wallet05);
//cryptoWalletsList.add(wallet06);