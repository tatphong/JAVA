package GUI_Client;

import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.prism.Image;
import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import com.sun.xml.internal.ws.api.server.Container;

public class Login extends JFrame{
	JTextField txtUserName;//email
	JPasswordField txtPassWord;
	JButton btnDangNhap;
	JButton btnDangKy;
	public Login(String tieude) 
	{
		super(tieude);
		addConTrols();
		addEvents();
		
		
		
		
		
	}
	public void addConTrols()
	{
		java.awt.Container con=getContentPane();
		JPanel pnMain=new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		JPanel pnTitle=new JPanel();
		con.add(pnMain);
		
		//Phần tiêu đề
		pnTitle.setLayout(new FlowLayout());
		//ImageIcon icon=new ImageIcon("Image/login.png");
		JLabel lblTitle=new JLabel(new ImageIcon("hinh/login2.png"));
		//lblTitle.setIcon(icon);
		pnTitle.add(lblTitle);
		pnMain.add(pnTitle);
		
		//Phần thông tin
		JPanel pnText1=new JPanel();
		pnText1.setLayout(new FlowLayout());
		JLabel lblUserName=new JLabel("User name");
		txtUserName=new JTextField(15);
		pnText1.add(lblUserName);
		pnText1.add(txtUserName);
		pnMain.add(pnText1);
		
		JPanel pnText2=new JPanel();
		pnText2.setLayout(new FlowLayout());
		JLabel lblPassword=new JLabel("Password");
		txtPassWord=new JPasswordField(15);
		pnText2.add(lblPassword);
		pnText2.add(txtPassWord);
		pnMain.add(pnText2);
		
		//Phần button
		JPanel pnButton=new JPanel();
		pnButton.setLayout(new FlowLayout());
		btnDangNhap=new JButton("Login");
		btnDangKy=new JButton("Register");
		ImageIcon iconButton1=new ImageIcon("hinh/login32.png");
		ImageIcon iconButton2=new ImageIcon("hinh/edit32.png");
		btnDangKy.setIcon(iconButton2);
		btnDangNhap.setIcon(iconButton1);
		pnButton.add(btnDangNhap);
		pnButton.add(btnDangKy);
		pnMain.add(pnButton);
		btnDangNhap.setPreferredSize(btnDangKy.getPreferredSize());
		
		
		
		
	}
	public void addEvents()
	{
		
	}
	public void showWindow()
	{
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	
	
	

}
