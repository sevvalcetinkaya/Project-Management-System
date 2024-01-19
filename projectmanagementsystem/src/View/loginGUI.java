package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.a.TextResultsetReader;
import com.mysql.cj.x.protobuf.MysqlxCrud.Projection;

import Helper.DBConnection;
import Helper.Helper;
import Model.Project;
import Model.User;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class loginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_username;
	private JTextField fld_uname;
	private JTextField fld_usurname;
	private JTextField fld_uusername;
	private DBConnection conn = new DBConnection();
	private User user = new User();
	private JPasswordField fld_userpass;
	private JPasswordField fld_upass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginGUI frame = new loginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginGUI() {
		setResizable(false);
		setTitle("Proje Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(176, 196, 222));
		w_pane.setForeground(new Color(0, 100, 0));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(loginGUI.class.getResource("logo5.png")));
		lbl_logo.setBounds(334, 10, 126, 119);
		w_pane.add(lbl_logo);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 112, 766, 613);
		w_pane.add(w_tabpane);
		
		JPanel w_userlogin = new JPanel();
		w_userlogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Kullanıcı Girişi", null, w_userlogin, null);
		w_userlogin.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kullanıcı Adı:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_1.setBounds(91, 142, 136, 38);
		w_userlogin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Şifre:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(91, 273, 136, 38);
		w_userlogin.add(lblNewLabel_1_1);
		
		JButton btn_login = new JButton("GİRİŞ YAP");
		btn_login.setBackground(new Color(176, 196, 222));
		btn_login.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_username.getText().length() == 0 || fld_userpass.getText().length() == 0) {
					Helper.showMsg("empty");
				} else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT *FROM user");
						while (rs.next()) {
							if (fld_username.getText().equals(rs.getString("username")) 
									&& fld_userpass.getText().equals(rs.getString("password"))) {
								Project pro = new Project();
								projectGUI pGUI = new projectGUI(pro);
								pGUI.setVisible(true);
								dispose();
								key = false;
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (key) Helper.showMsg("Kayıtlı kullanıcı yok, kayıt olunuz.");
				} 
			}
		});
		btn_login.setBounds(313, 401, 136, 49);
		w_userlogin.add(btn_login);
		
		fld_username = new JTextField();
		fld_username.setBounds(313, 146, 283, 38);
		w_userlogin.add(fld_username);
		fld_username.setColumns(10);
		
		fld_userpass = new JPasswordField();
		fld_userpass.setBounds(313, 277, 283, 38);
		w_userlogin.add(fld_userpass);
		
		JPanel w_usersignup = new JPanel();
		w_usersignup.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Kayıt Ol", null, w_usersignup, null);
		w_usersignup.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Adınız:");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_2.setBounds(101, 93, 165, 35);
		w_usersignup.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Soyadınız:");
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_3.setBounds(101, 159, 129, 37);
		w_usersignup.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Kullanıcı Adınız:");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_4.setBounds(101, 226, 153, 32);
		w_usersignup.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Şifreniz:");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_5.setBounds(101, 299, 129, 35);
		w_usersignup.add(lblNewLabel_5);
		
		fld_uname = new JTextField();
		fld_uname.setBounds(357, 96, 260, 35);
		w_usersignup.add(fld_uname);
		fld_uname.setColumns(10);
		
		fld_usurname = new JTextField();
		fld_usurname.setBounds(357, 163, 260, 35);
		w_usersignup.add(fld_usurname);
		fld_usurname.setColumns(10);
		
		fld_uusername = new JTextField();
		fld_uusername.setBounds(357, 228, 260, 35);
		w_usersignup.add(fld_uusername);
		fld_uusername.setColumns(10);
		
		JButton btn_signup = new JButton("KAYIT OL");
		btn_signup.setBackground(new Color(176, 196, 222));
		btn_signup.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_uname.getText().length() == 0 || fld_usurname.getText().length() == 0 || fld_uusername.getText().length() == 0
						|| fld_upass.getText().length() == 0) {
					Helper.showMsg("empty");
				} else {
					boolean control = user.register(fld_uname.getText(), fld_usurname.getText(), fld_upass.getText(), fld_uusername.getText());
					if (control) {
						Helper.showMsg("success");
						loginGUI login = new loginGUI();
						login.setVisible(true);
						dispose();
					} else {
						Helper.showMsg("error");
					}
				}
			}
		});
		btn_signup.setBounds(289, 431, 153, 39);
		w_usersignup.add(btn_signup);
		
		fld_upass = new JPasswordField();
		fld_upass.setBounds(357, 302, 260, 35);
		w_usersignup.add(fld_upass);
	}
}
