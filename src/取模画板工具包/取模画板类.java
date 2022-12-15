package 取模画板工具包;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

@SuppressWarnings("serial")
public class 取模画板类 extends JPanel implements MouseListener, ActionListener {

	String 取模字符 = "永";
	Font 取模字体 = new Font("黑体", Font.PLAIN, 24);

	int 字模宽度 = 24;
	int 字模高度 = 24;
	int 字模像素位数 = 1;

	int 单元格边长 = 10;// 应保证单元格边长>=2才能有显示效果

	boolean 取模方式 = true;// true为横向

	int 横向偏移 = 0;
	int 纵向偏移 = 0;

	JPopupMenu 右键菜单 = new JPopupMenu();
	JMenuItem 菜单项_字模左移一格 = new JMenuItem("字模左移一格");
	JMenuItem 菜单项_字模右移一格 = new JMenuItem("字模右移一格");
	JMenuItem 菜单项_字模上移一格 = new JMenuItem("字模上移一格");
	JMenuItem 菜单项_字模下移一格 = new JMenuItem("字模下移一格");

	BufferedImage 点阵图片;
	int 像素矩阵[][] = new int[24][24];
	int 字模数组[] = new int[72];

	public 取模画板类() {
		super();

		右键菜单.add(菜单项_字模左移一格);
		右键菜单.add(菜单项_字模右移一格);
		右键菜单.add(菜单项_字模上移一格);
		右键菜单.add(菜单项_字模下移一格);

		菜单项_字模左移一格.addActionListener(this);
		菜单项_字模右移一格.addActionListener(this);
		菜单项_字模上移一格.addActionListener(this);
		菜单项_字模下移一格.addActionListener(this);

		右键菜单.addMouseListener(this);

		this.addMouseListener(this);

		取模();
	}

	static int RGB到像素值(int RGB, int 像素位数) {
		int R = 0xff & RGB;
		int 像素值 = R * ((1 << 像素位数) - 1) / 255;
		像素值 = ((1 << 像素位数) - 1) - 像素值;
		return 像素值;
	}

	static int 像素值到RGB(int 像素值, int 像素位数) {
		int R = 255;
		if (像素值 != 0)
			R = 255 - 像素值 * 255 / ((1 << 像素位数) - 1);
		int RGB = 0xffffff & ((R << 16) | (R << 8) | R);
		return RGB;
	}

	static String INT转16进制字符串(int x) {
		String 十六进制字符表 = "0123456789abcdef";
		String str = "0x";
		str = str + String.valueOf(十六进制字符表.charAt(0x0f & (x >> 4)));
		str = str + String.valueOf(十六进制字符表.charAt(0x0f & x));
		return str;
	}

	private void 重设画板大小() {
		this.setSize(字模宽度 * 单元格边长 + 1, 字模高度 * 单元格边长 + 1);
	}

	public void paint(Graphics g2d) {
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		// 绘制栅格
		g2d.setColor(Color.red);
		for (int i = 0; i <= 字模宽度; i++)
			g2d.drawLine(i * 单元格边长, 0, i * 单元格边长, this.getHeight());
		for (int i = 0; i <= 字模高度; i++)
			g2d.drawLine(0, i * 单元格边长, this.getWidth(), i * 单元格边长);

		// 绘制色块
		for (int j = 0; j < 字模高度; j++)
			for (int i = 0; i < 字模宽度; i++) {
				g2d.setColor(new Color(像素值到RGB(像素矩阵[j][i], 字模像素位数)));
				g2d.fillRect(i * 单元格边长 + 1, j * 单元格边长 + 1, 单元格边长 - 1, 单元格边长 - 1);
			}
	}

	private void 生成点阵图片() {
		点阵图片 = new BufferedImage(字模宽度, 字模高度, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) 点阵图片.createGraphics();

		// 启用抗锯齿
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// 清屏
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, 字模宽度, 字模高度);
		g2d.setFont(取模字体);
		g2d.setColor(Color.black);
		g2d.drawString(取模字符, 0 + 横向偏移, (int) (字模高度) + 纵向偏移);
		g2d.dispose();// 释放对象

		return;
	}

	private void 生成像素矩阵() {
		像素矩阵 = new int[字模高度][字模宽度];
		
		for (int j = 0; j < 字模高度; j++) {
			for (int i = 0; i < 字模宽度; i++) {
				像素矩阵[j][i] = RGB到像素值(点阵图片.getRGB(i, j), 字模像素位数);
				System.out.print(像素矩阵[j][i]+" ");
			}
			System.out.println();
		}
		return;
	}

	private void 生成字模数组() {
		int n = 字模宽度 * 字模高度 * 字模像素位数;
		n = n / 8 + (n % 8 == 0 ? 0 : 1);
		字模数组 = new int[n];

		int k = 0;

		if (取模方式 == true) {
			for (int j = 0; j < 字模高度; j++)
				for (int i = 0; i < 字模宽度; i++) {
					if (取模方式 == true)
						k = (j * 字模宽度 + i) / (8 / 字模像素位数);
					字模数组[k] = 字模数组[k] << 字模像素位数;
					字模数组[k] = 字模数组[k] | 像素矩阵[j][i];
				}
		} else {
			for (int i = 0; i < 字模宽度; i++)
				for (int j = 0; j < 字模高度; j++) {
					if (取模方式 == true)
						k = (i * 字模高度 + j) / (8 / 字模像素位数);
					字模数组[k] = 字模数组[k] << 字模像素位数;
					字模数组[k] = 字模数组[k] | 像素矩阵[j][i];
				}
		}

		return;
	}

	private String 生成C代码() {
		// 生成C代码
		String 代码 = "{//\"" + 取模字符 + "\"\n";
		for (int i = 0; i < 字模数组.length; i++) {
			代码 = 代码 + INT转16进制字符串(字模数组[i]);
			if (i == 字模数组.length - 1)
				break;
			代码 = 代码 + ",";
		}
		代码 = 代码 + "},//\"" + 取模字符 + "\"\n";

		return 代码;
	}

	public void 输出参数() {
		System.out.println(取模字符.toString());
		System.out.println(取模字体.toString());
		System.out.println(字模宽度);
		System.out.println(字模高度);
		System.out.println(单元格边长);
		System.out.println(取模方式);
		System.out.println(字模像素位数);
		System.out.println(横向偏移);
		System.out.println(纵向偏移);

		return;
	}

	public String 取模() {
		生成点阵图片();
		生成像素矩阵();
		生成字模数组();
		重设画板大小();
		this.repaint();

		return 生成C代码();
	}

	private void 修改像素点(int x, int y) {
		if (像素矩阵[y][x] == (2 << 字模像素位数) - 1)
			像素矩阵[y][x] = 0;
		else
			像素矩阵[y][x]++;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {// 鼠标左键
			int x = e.getX() / 单元格边长;
			int y = e.getY() / 单元格边长;
			修改像素点(x, y);
		} else if (e.getButton() == MouseEvent.BUTTON3) // 鼠标右键
			右键菜单.show(e.getComponent(), e.getX(), e.getY());
		this.repaint();
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.菜单项_字模左移一格) {
			this.横向偏移--;
		} else if (e.getSource() == this.菜单项_字模右移一格) {
			this.横向偏移++;
		} else if (e.getSource() == this.菜单项_字模上移一格) {
			this.纵向偏移--;
		} else if (e.getSource() == this.菜单项_字模下移一格)
			this.纵向偏移++;
		取模();
		repaint();
	}
}