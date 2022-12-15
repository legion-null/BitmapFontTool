package 取模画板工具包;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class 主界面 extends JFrame implements ActionListener {
	public JPanel 取模画板面板;
	public 取模画板类 取模画板;
	public JPanel 设置面板;
	public JLabel 字体设置标签;
	public JComboBox<String> 字体选择组合框;
	public JLabel 字模宽度标签;
	public JTextField 字体大小文本域;
	public JComboBox<String> 字体风格选择组合框;
	public JLabel 字模高度标签;
	public JLabel 画板单元格边长标签;
	public JTextField 画板单元格边长文本域;
	public JTextField 字模宽度文本域;
	public JTextField 字模高度文本域;
	public JTextField 取模字符输入框;
	public JLabel 取模方式标签;
	public JComboBox<String> 取模方式选择组合框;
	public JLabel 抗锯齿程度标签;
	public JComboBox<String> 抗锯齿程度选择组合框;
	public JButton 取模按钮;
	public JPanel 输出面板;
	public JMenuBar 菜单栏;
	public JMenu 关于菜单;
	public JScrollPane 输出文本滚动面板;
	public JTextArea 输出文本区;
	public JButton 手动生成点阵按钮;

	// 变量
	JPanel 批量取模面板;
	JScrollPane 文本输入滚动面板;
	JTextArea 文本输入区;
	JButton 批量取模按钮;
	JMenuItem 关于本软件菜单项;
	JMenuItem 关于作者菜单项;

	public 主界面() {
		super();
		setIconImage(Toolkit.getDefaultToolkit().getImage(主界面.class.getResource("/图标.jpg")));
		initialize();

		this.setVisible(true);
	}

	private void initialize() {
		setTitle("字体取模工具 作者：LEGION");
		setBounds(100, 100, 1000, 800);
		getContentPane().setLayout(
				new MigLayout("", "[400px:400px:400px,grow,fill][grow]", "[400px:400px:400px,grow,fill][grow]"));

		this.取模画板面板 = new JPanel();
		this.取模画板面板.setBorder(
				new TitledBorder(null, "\u53D6\u6A21\u753B\u677F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(this.取模画板面板, "cell 0 0,grow");
		this.取模画板面板.setLayout(null);

		this.取模画板 = new 取模画板类();
		this.取模画板.setLocation(10, 20);
		this.取模画板面板.add(this.取模画板);

		this.手动生成点阵按钮 = new JButton("手动生成点阵");
		this.手动生成点阵按钮.addActionListener(this);
		this.手动生成点阵按钮.setBounds(280, 350, 100, 30);
		this.取模画板面板.add(this.手动生成点阵按钮);
		
		this.设置面板 = new JPanel();
		this.设置面板.setBorder(
				new TitledBorder(null, "设置", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		getContentPane().add(this.设置面板, "cell 1 0,grow");
		this.设置面板.setLayout(new MigLayout("", "[left][grow,fill][100px:100px:100px,grow][60px:60px:60px,grow,fill]",
				"[][][][][][][][][grow][100px:100px:100px,fill]"));

		this.字体设置标签 = new JLabel("字体设置：");
		this.设置面板.add(this.字体设置标签, "cell 0 0,alignx left");

		GraphicsEnvironment 图形设备环境 = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] 本机所有可用字体列表 = 图形设备环境.getAvailableFontFamilyNames();
		this.字体选择组合框 = new JComboBox<String>(本机所有可用字体列表);
		this.字体选择组合框.addActionListener(this);
		this.设置面板.add(this.字体选择组合框, "flowx,cell 1 0,growx");

		String[] 本机所有可用字体风格列表 = { "正常", "粗体", "斜体" };
		this.字体风格选择组合框 = new JComboBox<String>(本机所有可用字体风格列表);
		this.字体风格选择组合框.addActionListener(this);
		this.设置面板.add(this.字体风格选择组合框, "cell 2 0,growx");

		this.字体大小文本域 = new JTextField();
		this.字体大小文本域.setText("24");
		this.设置面板.add(this.字体大小文本域, "cell 3 0");

		this.字模宽度标签 = new JLabel("字模宽度：");
		this.设置面板.add(this.字模宽度标签, "cell 0 3,alignx left");

		this.字模宽度文本域 = new JTextField();
		this.字模宽度文本域.setText("24");
		this.设置面板.add(this.字模宽度文本域, "cell 1 3");

		this.字模高度标签 = new JLabel("字模高度：");
		this.设置面板.add(this.字模高度标签, "cell 0 4,alignx left");

		this.字模高度文本域 = new JTextField();
		this.字模高度文本域.setText("24");
		this.设置面板.add(this.字模高度文本域, "cell 1 4");

		this.画板单元格边长标签 = new JLabel("画板单元格边长：");
		this.设置面板.add(this.画板单元格边长标签, "cell 0 5,alignx left");

		this.画板单元格边长文本域 = new JTextField();
		this.画板单元格边长文本域.setText("10");
		this.设置面板.add(this.画板单元格边长文本域, "cell 1 5");

		this.取模方式标签 = new JLabel("取模方式：");
		this.设置面板.add(this.取模方式标签, "cell 0 7,alignx left");

		String[] 取模方式列表 = { "横向取模", "纵向取模" };
		this.取模方式选择组合框 = new JComboBox<String>(取模方式列表);
		this.取模方式选择组合框.addActionListener(this);
		this.设置面板.add(this.取模方式选择组合框, "cell 1 7,growx");

		this.抗锯齿程度标签 = new JLabel("抗锯齿程度:");
		this.抗锯齿程度标签.setVerticalAlignment(SwingConstants.BOTTOM);
		this.设置面板.add(抗锯齿程度标签, "cell 2 7,alignx left");

		String[] 抗锯齿程度列表 = { "1bpp", "2bpp", "4bpp" };
		this.抗锯齿程度选择组合框 = new JComboBox<String>(抗锯齿程度列表);
		this.设置面板.add(抗锯齿程度选择组合框, "cell 3 7,growx");

		this.取模字符输入框 = new JTextField();
		this.取模字符输入框.setHorizontalAlignment(SwingConstants.CENTER);
		this.取模字符输入框.setText("永");
		this.取模字符输入框.setFont(new Font("文泉驿微米黑", Font.PLAIN, 80));
		this.取模字符输入框.setSize(100, 100);
		this.设置面板.add(this.取模字符输入框, "cell 0 9,growx");

		this.取模按钮 = new JButton("取模");
		this.取模按钮.addActionListener(this);
		this.设置面板.add(this.取模按钮, "cell 2 9 2 1,growx");

		this.批量取模面板 = new JPanel();
		this.批量取模面板.setBorder(
				new TitledBorder(null, "批量取模", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		getContentPane().add(this.批量取模面板, "cell 0 1,grow");
		this.批量取模面板.setLayout(new MigLayout("", "[grow][100px:100px:100px,grow,fill]", "[grow][]"));

		this.文本输入滚动面板 = new JScrollPane();
		this.文本输入滚动面板.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.文本输入滚动面板.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.批量取模面板.add(this.文本输入滚动面板, "cell 0 0 2 1,grow");

		this.文本输入区 = new JTextArea();
		this.文本输入区.setLineWrap(true);
		this.文本输入区.setFont(new Font("文泉驿等宽微米黑", Font.PLAIN, 14));
		this.文本输入滚动面板.setViewportView(this.文本输入区);

		this.批量取模按钮 = new JButton("确认");
		this.批量取模按钮.addActionListener(this);
		this.批量取模面板.add(this.批量取模按钮, "cell 1 1");

		this.输出面板 = new JPanel();
		this.输出面板.setBorder(new TitledBorder(null, "输出", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(this.输出面板, "cell 1 1,grow");
		this.输出面板.setLayout(new GridLayout(1, 0, 0, 0));

		this.输出文本滚动面板 = new JScrollPane();
		this.输出文本滚动面板.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.输出文本滚动面板.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.输出面板.add(this.输出文本滚动面板);

		this.输出文本区 = new JTextArea();
		this.输出文本区.setLineWrap(true);
		this.输出文本区.setFont(new Font("文泉驿等宽微米黑", Font.PLAIN, 14));
		this.输出文本滚动面板.setViewportView(this.输出文本区);

		this.菜单栏 = new JMenuBar();
		setJMenuBar(this.菜单栏);

		this.关于菜单 = new JMenu("关于");
		this.菜单栏.add(this.关于菜单);

		this.关于本软件菜单项 = new JMenuItem("关于本软件");
		this.关于本软件菜单项.addActionListener(this);
		this.关于菜单.add(this.关于本软件菜单项);

		this.关于作者菜单项 = new JMenuItem("关于作者");
		this.关于作者菜单项.addActionListener(this);
		this.关于菜单.add(this.关于作者菜单项);
	}

	private Font 字体设置() {
		String 字体名称 = (String) this.字体选择组合框.getSelectedItem();
		int 字体风格 = 0;
		switch (this.字体风格选择组合框.getSelectedIndex()) {
		case 0:
			字体风格 = Font.PLAIN;
			break;
		case 1:
			字体风格 = Font.BOLD;
			break;
		case 2:
			字体风格 = Font.ITALIC;
			break;
		}
		int 字体大小 = Integer.valueOf(this.字体大小文本域.getText());
		return new Font(字体名称, 字体风格, 字体大小);
	}
	
	public void 取模参数设置() {
		this.取模画板.取模字符 = this.取模字符输入框.getText();
		this.取模画板.取模字体 = this.字体设置();
		this.取模画板.字模宽度 = Integer.valueOf(this.字模宽度文本域.getText());
		this.取模画板.字模高度 = Integer.valueOf(this.字模高度文本域.getText());
		this.取模画板.单元格边长 = Integer.valueOf(this.画板单元格边长文本域.getText());
		this.取模画板.取模方式 = (this.取模方式选择组合框.getSelectedIndex() == 0 ? true : false);
		this.取模画板.字模像素位数 = (0x01 << this.抗锯齿程度选择组合框.getSelectedIndex());
	}

	public void 批量取模() {
		char[] 待取模字符数组 = this.文本输入区.getText().toCharArray();

		取模参数设置();
		for (char 字符 : 待取模字符数组) {
			this.取模字符输入框.setText(String.valueOf(字符));
			this.取模画板.取模字符 = this.取模字符输入框.getText();
			this.输出文本区.append(this.取模画板.取模());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == this.取模按钮) {
			取模参数设置();
			this.输出文本区.append(this.取模画板.取模());
		}
		if (e.getSource() == this.手动生成点阵按钮) {

		}
		if (e.getSource() == this.批量取模按钮) {
			批量取模();
		}
		if (e.getSource() == this.关于本软件菜单项) {
			new 关于本软件();
		}
		if (e.getSource() == this.关于作者菜单项) {
			new 关于作者();
		}
	}



	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		new 主界面();
	}
}
