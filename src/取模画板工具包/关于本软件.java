package 取模画板工具包;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class 关于本软件 extends JFrame {
	JTabbedPane 选项卡面板;
	JScrollPane 软件说明文本滚动面板;
	JScrollPane 软件协议说明滚动面板;
	JTextPane 软就说明文本面板;
	JTextPane 许可协议文本面板;

	public 关于本软件() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(关于本软件.class.getResource("/图标.jpg")));
		initialize();

		setVisible(true);
	}
	private void initialize() {
		setTitle("关于本软件");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][100px:100px:100px,grow,fill]", "[grow]"));
		
		this.选项卡面板 = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(this.选项卡面板, "cell 0 0 2 1,grow");
		
		this.软件说明文本滚动面板 = new JScrollPane();
		this.选项卡面板.addTab("本软件使用说明", null, this.软件说明文本滚动面板, null);
		
		this.软就说明文本面板 = new JTextPane();
		this.软就说明文本面板.setEditable(false);
		this.软就说明文本面板.setFont(new Font("SansSerif", Font.PLAIN, 14));
		this.软就说明文本面板.setText("本软件为LEGION开发的字体取模软件。\r\n本软件使用Java开发。\r\n本软件支持单字取模,，手动取模和批量取模。\r\n本软件支持横向取模和纵向取模两种取模方式。\r\n\r\n以下代码可供参考和使用：\r\n横向取模：\r\nvoid drawChar(u8 **font,u8 index,u8 x,u8 y,u8 width,u8 height)\r\n{\r\n    u8 i,j;\r\n    for(j=0;j<height;j++)\r\n        for(i=0;i<width;i++)\r\n            if((font[index][j]>>(7-i))==0x00)\r\n                drawPoint(i,j);\r\n}\r\n\r\n纵向取模：\r\nvoid drawChar(u8 **font,u8 index,u8 x,u8 y,u8 width,u8 height)\r\n{\r\n    u8 i,j;\r\n    for(i=0;i<width;i++)\r\n        for(j=0;j<height;j++)\r\n            if((font[index][i]>>(7-j))==0x00)\r\n                drawPoint(i,j);\r\n}\r\n\r\n2021年7月27日 LEGION");
		this.软件说明文本滚动面板.setViewportView(this.软就说明文本面板);
		
		this.软件协议说明滚动面板 = new JScrollPane();
		this.选项卡面板.addTab("许可协议", null, this.软件协议说明滚动面板, null);
		
		this.许可协议文本面板 = new JTextPane();
		this.许可协议文本面板.setEditable(false);
		this.许可协议文本面板.setText("MIT License\r\n\r\nCopyright (c) 2021 LEGION\r\n\r\nPermission is hereby granted, free of charge, to any person obtaining a copy\r\nof this software and associated documentation files (the \"Software\"), to deal\r\nin the Software without restriction, including without limitation the rights\r\nto use, copy, modify, merge, publish, distribute, sublicense, and/or sell\r\ncopies of the Software, and to permit persons to whom the Software is\r\nfurnished to do so, subject to the following conditions:\r\n\r\nThe above copyright notice and this permission notice shall be included in all\r\ncopies or substantial portions of the Software.\r\n\r\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\r\nIMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\r\nFITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\r\nAUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\r\nLIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\r\nOUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\r\nSOFTWARE.\r\n");
		this.软件协议说明滚动面板.setViewportView(this.许可协议文本面板);
	}
}
