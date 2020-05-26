package com.springboot.controller;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 锟斤拷证锟斤拷
 */
@Controller
@RequestMapping("/validateCodeController")
public class ValidateCodeController {
	private char code[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
			'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2',
			'3', '4', '5', '6', '7', '8', '9' };
	private static final int WIDTH = 50;
	private static final int HEIGHT = 20;
	private static final int LENGTH = 4;
	@RequestMapping("/validateCode")
	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 锟斤拷锟斤拷锟斤拷应锟斤拷头锟斤拷息
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 锟斤拷锟斤拷锟斤拷应锟斤拷MIME锟斤拷锟斤拷
		response.setContentType("image/jpeg");

		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Font mFont = new Font("Arial", Font.TRUETYPE_FONT, 18);
		Graphics g = image.getGraphics();
		Random rd = new Random();

		// 锟斤拷锟矫憋拷锟斤拷锟斤拷色
		g.setColor(new Color(rd.nextInt(55) + 200, rd.nextInt(55) + 200, rd
				.nextInt(55) + 200));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		// 锟斤拷锟斤拷锟斤拷锟斤拷
		g.setFont(mFont);

		// 锟斤拷锟竭匡拷
		g.setColor(Color.black);
		g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

		// 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟街わ拷锟�
		String result = "";
		for (int i = 0; i < LENGTH; ++i) {
			result += code[rd.nextInt(code.length)];
		}
		HttpSession session = request.getSession();
		session.setAttribute("code", result);

		// 锟斤拷锟斤拷证锟斤拷
		for (int i = 0; i < result.length(); i++) {
			g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
					.nextInt(200)));
			g.drawString(result.charAt(i) + "", 12 * i + 1, 16);
		}

		// 锟斤拷锟斤拷锟斤拷锟�2锟斤拷锟斤拷锟斤拷锟斤拷
		for (int i = 0; i < 2; i++) {
			g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
					.nextInt(200)));
			int x1 = rd.nextInt(WIDTH);
			int x2 = rd.nextInt(WIDTH);
			int y1 = rd.nextInt(HEIGHT);
			int y2 = rd.nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}

		// 锟酵凤拷图锟斤拷锟斤拷源
		g.dispose();

			OutputStream os = response.getOutputStream();

			// 锟斤拷锟酵硷拷锟揭筹拷锟�
			ImageIO.write(image, "JPEG", os);
		
	}
}
