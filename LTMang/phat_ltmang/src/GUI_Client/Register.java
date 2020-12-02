package GUI_Client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.sun.xml.internal.ws.api.server.Container;
import com.toedter.calendar.JDateChooser;

public class Register extends JFrame
{
  JTextField txtUser;
  JPasswordField txtPass;
  JPasswordField txtEnterPass;
  ButtonGroup btnGender;
  JRadioButton rdbMale;
  JRadioButton rdbWomen;
  JDateChooser dateChooser;
  JButton btnRegister;
  JButton btnLogin;
public Register(String tieude)
{
	 this.setTitle(tieude);
	 addControls();
}
public void addControls() 
{
	java.awt.Container con=getContentPane();
	JPanel pnMain=new JPanel();
	pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
	con.add(pnMain);
	
	//JLabel ảnh tiêu đề
	JPanel pnTitle=new JPanel();
	pnTitle.setLayout(new FlowLayout());
	JLabel lblTitle=new JLabel();
	ImageIcon iconTitle=new ImageIcon("hinh/register (1).png");
	lblTitle.setIcon(iconTitle);
	pnTitle.add(lblTitle);
	pnMain.add(pnTitle);
	
	//Phần User
	JPanel pnUser=new JPanel();
	pnUser.setLayout(new FlowLayout());
	JLabel lblUser=new JLabel("User name");
	txtUser =new JTextField(20);
	pnUser.add(lblUser);
	pnUser.add(txtUser);
	pnMain.add(pnUser);
	
	//Phần Password
	JPanel pnPass=new JPanel();
	pnPass.setLayout(new FlowLayout());
	JLabel lblPass=new JLabel("Password");
	txtPass=new JPasswordField(20);
	pnPass.add(lblPass);
	pnPass.add(txtPass);
	pnMain.add(pnPass);
	
	//Phần EnterPass
	JPanel pnEnterPass=new JPanel();
	pnEnterPass.setLayout(new FlowLayout());
	JLabel lblEnterPass=new JLabel("Enter pass");
	txtEnterPass=new JPasswordField(20);
	pnEnterPass.add(lblEnterPass);
	pnEnterPass.add(txtEnterPass);
	pnMain.add(pnEnterPass);
	
	//Phần date
	JPanel pnDate=new JPanel();
	pnDate.setLayout(new FlowLayout());
	JLabel lblDate=new JLabel("Date");
	JDateChooser dateChooser = new JDateChooser();
	dateChooser.setDateFormatString("dd-MM-yyyy");
	dateChooser.setPreferredSize(new Dimension(222,20));
	BorderLayout borderLayout = (BorderLayout) dateChooser.getLayout();
	dateChooser.getJCalendar().setPreferredSize(new Dimension(300, 400));
	pnDate.add(lblDate);
	pnDate.add(dateChooser);
	pnMain.add(pnDate);
	
	//Phần gender
	JPanel pnGender=new JPanel();
	pnGender.setLayout(new FlowLayout());
	rdbMale=new JRadioButton("Male");
	rdbWomen=new JRadioButton("Women");
	btnGender=new ButtonGroup();
	btnGender.add(rdbMale);
	btnGender.add(rdbWomen);
	pnGender.add(rdbMale);
	pnGender.add(rdbWomen);
	pnMain.add(pnGender);
	
	//Phần button
	JPanel pnButton=new JPanel();
	pnButton.setLayout(new FlowLayout());
	btnLogin=new JButton("Login");
	btnRegister=new JButton("Register");
	ImageIcon iconButtonLogin=new ImageIcon("hinh/login32.png");
	ImageIcon iconButtonRegister=new ImageIcon("hinh/edit32.png");
	btnRegister.setIcon(iconButtonRegister);
	btnLogin.setIcon(iconButtonLogin);
	pnButton.add(btnLogin);
	pnButton.add(btnRegister);
	pnMain.add(pnButton);
	
	lblDate.setPreferredSize(lblUser.getPreferredSize());
	lblDate.setPreferredSize(lblPass.getPreferredSize());
	lblDate.setPreferredSize(lblEnterPass.getPreferredSize());

	
	
}
public void showWindow()
{
	this.setSize(400, 400);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setVisible(true);
}
  
  
  
}
