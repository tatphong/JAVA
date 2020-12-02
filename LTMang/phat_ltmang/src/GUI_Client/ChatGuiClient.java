package GUI_Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javafx.scene.layout.Border;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import com.sun.xml.internal.ws.api.server.Container;

public class ChatGuiClient extends JFrame{
	JMenuBar mnuBar;
	JMenuItem mnuChatApp;
	JMenuItem mnuContact;
	JMenuItem mnuView;
	JMenuItem mnuHelp;
	JTextField txtSearch;
	JButton btnSearch;
	JTabbedPane tab;
	JTextField txtChat;
	JButton btnSend;
	JButton btnFile;
	
 public ChatGuiClient(String tieude)
 {
	 this.setTitle(tieude);
	 addControls();
	 addEvents();
 }
 public void addEvents() 
 {
	mnuBar=new JMenuBar();
	setJMenuBar(mnuBar);
	mnuChatApp=new JMenuItem("ChatApp");
	mnuBar.add(mnuChatApp);
	mnuContact=new JMenuItem("Contact");
	mnuBar.add(mnuContact);
	mnuView=new JMenuItem("View");
	mnuBar.add(mnuView);
	mnuHelp=new JMenuItem("Help");
	mnuBar.add(mnuHelp);
	JTextPane txtPane;
	
	java.awt.Container con=getContentPane();
	JPanel pnRight=new JPanel();
	JPanel pnLeft=new JPanel();
	pnLeft.setLayout(new BorderLayout());
	JSplitPane sp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pnLeft,pnRight);
	con.add(sp);
	
	//Phần giao diện bên trái
	pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
	
	//Phần trên bên trái
	JPanel pnMainTop=new JPanel();
	pnMainTop.setLayout(new BoxLayout(pnMainTop, BoxLayout.Y_AXIS));
	
	JPanel pnLeftTop=new JPanel();
	pnLeftTop.setLayout(new BoxLayout(pnLeftTop, BoxLayout.Y_AXIS));
	
	JLabel lblLeftTop=new JLabel();
	ImageIcon iconlblLeftTop=new ImageIcon("hinh/login2.png");
	lblLeftTop.setIcon(iconlblLeftTop);
	
	JPanel pnLeftRight=new JPanel();
	pnLeftRight.setLayout(new FlowLayout());
	
	JPanel pnLeftTopTopLeft=new JPanel();
	pnLeftTopTopLeft.setLayout(new BoxLayout(pnLeftTopTopLeft, BoxLayout.X_AXIS));
	pnLeftTopTopLeft.add(lblLeftTop);//phần ảnh
	
	
	JPanel pnLeftTopTopRight=new JPanel();
	pnLeftTopTopRight.setLayout(new BoxLayout(pnLeftTopTopRight, BoxLayout.Y_AXIS));

	JLabel lblTopTopRight=new JLabel("User name");
    JLabel lblTopTopBottom=new JLabel("Demo cht app - Assignment1");
    pnLeftTopTopRight.add(lblTopTopRight);
    pnLeftTopTopRight.add(lblTopTopBottom);
    pnLeftRight.add(pnLeftTopTopLeft);
    pnLeftRight.add(pnLeftTopTopRight);
    
    pnLeftTop.add(pnLeftRight);
    javax.swing.border.Border boder=BorderFactory.createLineBorder(Color.black);
    
    JPanel pnSearch=new JPanel();
    pnSearch.setLayout(new FlowLayout());
    btnSearch=new JButton("Find");
    ImageIcon iconSearch=new ImageIcon("hinh/search.png");
    btnSearch.setIcon(iconSearch);
    txtSearch=new JTextField("Search...", 20);
    txtSearch.setPreferredSize(new Dimension(80,40));
    pnSearch.add(btnSearch);
    pnSearch.add(txtSearch);
    
	pnLeft.add(pnLeftTop,BorderLayout.NORTH);
		
	//Phần bên trái-dưới
	JPanel pnTab=new JPanel();
	tab=new JTabbedPane();
	pnLeft.add(tab,BorderLayout.CENTER);
	
	JPanel pnTabContacts=new JPanel();
	pnTabContacts.setLayout(new BoxLayout(pnTabContacts, BoxLayout.Y_AXIS));
	JScrollPane sc=new JScrollPane(pnTabContacts,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JLabel lbluser1=new JLabel("Quân");
	JLabel lbluser2=new JLabel("Phát");
	JLabel lbluser3=new JLabel("Sang");
	JLabel lbluser4=new JLabel("Sang");
	JLabel lbluser5=new JLabel("Sang");
	JLabel lbluser6=new JLabel("Sang");
	JLabel lbluser7=new JLabel("Cường");
	pnTabContacts.add(lbluser1);
	pnTabContacts.add(lbluser2);
	pnTabContacts.add(lbluser3);
	pnTabContacts.add(lbluser4);
	pnTabContacts.add(lbluser5);
	pnTabContacts.add(lbluser6);
	pnTabContacts.add(lbluser7);
	
	
	JPanel pnTabGroup=new JPanel();
	tab.add(sc,"CONTACTS");
	tab.add(pnTabGroup,"GROUP");
    pnLeft.add(pnSearch,BorderLayout.SOUTH);
	
    //Phần bên phải
    pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
    JPanel pnRightImage=new JPanel();
    pnRightImage.setLayout(new FlowLayout());
    JLabel lblimageright=new JLabel();
    ImageIcon iconright=new ImageIcon("hinh/login2.png");
    lblimageright.setIcon(iconright);
    JPanel pnrighttext=new JPanel();
    pnrighttext.setLayout(new BoxLayout(pnrighttext, BoxLayout.Y_AXIS));
    JLabel lbluserchat=new JLabel("Cường");
    JLabel lblstatus=new JLabel("This is status");
    pnRightImage.add(lblimageright);
    pnrighttext.add(lbluserchat);
    pnrighttext.add(lblstatus);
    pnRightImage.add(pnrighttext);
    pnRight.add(pnRightImage);
    
    JPanel pntxtPane=new JPanel();
    pntxtPane.setLayout(new BorderLayout());
    txtPane=new JTextPane();
    JScrollPane scPane=new JScrollPane(txtPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
    StyleConstants.setBold(attributeSet, true);
    pntxtPane.add(scPane,BorderLayout.CENTER);
    pnRight.add(pntxtPane);
    
    JPanel pnChat=new JPanel();
    pnChat.setLayout(new FlowLayout());
    txtChat=new JTextField(34);
    txtChat.setPreferredSize(new Dimension(80,40));
    pnChat.add(txtChat);
    btnSend=new JButton();
    ImageIcon iconsend=new ImageIcon("hinh/send.png");
    btnSend.setIcon(iconsend);
    btnFile=new JButton();
    ImageIcon iconfile=new ImageIcon("hinh/attachment.png");
    btnFile.setIcon(iconfile);
    pnChat.add(btnSend);
    pnChat.add(btnFile);
    pnRight.add(pnChat);
	
	
}
public void addControls() {
	// TODO Auto-generated method stub
	
}
public void showWindow()
	{
		this.setSize(900, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
