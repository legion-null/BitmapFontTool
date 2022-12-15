package 取模画板工具包;

import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class 关于作者 extends JFrame {

	JPanel 收款码图片面板 = new JPanel();
	JScrollPane 作者介绍滚动面板 = new JScrollPane();
	JTextPane 作者介绍面板 = new JTextPane();
	JLabel 收款码图片标签;

	public 关于作者() {
		super();
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(关于作者.class.getResource("/图标.jpg")));
		initialize();
		
		
		setVisible(true);
	}

	private void initialize() {
		setTitle("关于作者");
		setBounds(100, 100, 450, 431);
		getContentPane()
				.setLayout(new MigLayout("", "[grow][100px:100px:100px,grow,fill][200px:200px:200px,grow,fill]", "[320px:n,grow,fill]"));
		getContentPane().add(作者介绍滚动面板, "cell 0 0 2 1,grow");
		作者介绍面板.setEditable(false);
		作者介绍面板.setFont(new Font("SansSerif", Font.PLAIN, 14));
		作者介绍面板.setText(
				"作者：LEGION\r\n\r\n联系方式：\r\n   QQ：2767326088\r\n   邮箱：2767326088@qq.com\r\n\r\n开源的软件仓库：\n   GitHub：https://github.com/legion-null\r\n   码云：Gitee@LEGION-NULL\r\n\r\n欢迎投币支持作者。");
		作者介绍滚动面板.setViewportView(作者介绍面板);

		
		this.收款码图片标签 = new JLabel("");
		this.收款码图片标签.setIcon(new ImageIcon(关于作者.class.getResource("/收钱码.jpg")));
		getContentPane().add(this.收款码图片标签, "cell 2 0");
	}
}
