package View;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.DBConnection;
import Helper.Helper;
import Model.Project;
import Model.Task;
import Model.Worker;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class taskGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	static Task task = new Task();
	static Worker worker = new Worker();
	private DBConnection conn = new DBConnection();
	private JPanel w_pane;
	private JTextField fld_gad;
	private JTextField fld_cid;
	private JLabel lblNewLabel_2;
	private JTextField fld_pnum;
	private JLabel lblNewLabel_3;
	private JTextField fld_durum;
	private JButton btn_git;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_3_2;
	private JTextField fld_ttime;
	private JLabel lblNewLabel_4;
	private JTextField fld_tid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					taskGUI frame = new taskGUI(task);
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
	public taskGUI(Task task) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(176, 196, 222));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		btn_git = new JButton("GERİ");
		btn_git.setBackground(new Color(255, 255, 255));
		btn_git.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_git.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Project pro = new Project();
				projectGUI pGUI = null;
				try {
					pGUI = new projectGUI(pro);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pGUI.setVisible(true);
				dispose();
			}
		});
		btn_git.setBounds(679, 30, 85, 29);
		w_pane.add(btn_git);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 69, 754, 661);
		w_pane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GÖREV ADI:");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel.setBounds(196, 101, 124, 21);
		panel.add(lblNewLabel);
		
		fld_gad = new JTextField();
		fld_gad.setBounds(356, 100, 222, 29);
		panel.add(fld_gad);
		fld_gad.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ÇALIŞAN ID:");
		lblNewLabel_1.setBounds(196, 144, 121, 19);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		
		fld_cid = new JTextField();
		fld_cid.setBounds(356, 144, 222, 29);
		panel.add(fld_cid);
		fld_cid.setColumns(10);
		
		fld_pnum = new JTextField();
		fld_pnum.setBounds(356, 185, 222, 29);
		panel.add(fld_pnum);
		fld_pnum.setColumns(10);
		
		lblNewLabel_2 = new JLabel("PROJE NUMARASI:");
		lblNewLabel_2.setBounds(196, 187, 151, 19);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		
		lblNewLabel_3 = new JLabel("GÖREV DURUMU:");
		lblNewLabel_3.setBounds(196, 228, 151, 16);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		
		fld_durum = new JTextField();
		fld_durum.setBounds(356, 228, 222, 29);
		panel.add(fld_durum);
		fld_durum.setColumns(10);
		
		btnNewButton_1 = new JButton("GÜNCELLE");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(176, 196, 222));
		btnNewButton_1.setBounds(623, 271, 121, 29);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		
		JButton btn_gata = new JButton("GÖREV ATA");
		btn_gata.setForeground(new Color(255, 255, 255));
		btn_gata.setBackground(new Color(176, 196, 222));
		btn_gata.setBounds(297, 518, 196, 29);
		panel.add(btn_gata);
		btn_gata.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		
		btnNewButton = new JButton("ÇIKIŞ");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(176, 196, 222));
		btnNewButton.setBounds(643, 604, 85, 29);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		
		JLabel lblNewLabel_3_1 = new JLabel("BAŞLANGIÇ TARİHİ:");
		lblNewLabel_3_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(196, 331, 151, 16);
		panel.add(lblNewLabel_3_1);
		
		JDateChooser date_starttask = new JDateChooser();
		date_starttask.setBounds(356, 331, 222, 19);
		panel.add(date_starttask);
		
		lblNewLabel_3_2 = new JLabel("BİTİŞ TARİHİ:");
		lblNewLabel_3_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_3_2.setBounds(196, 384, 151, 16);
		panel.add(lblNewLabel_3_2);
		
		JDateChooser date_endtask = new JDateChooser();
		date_endtask.setBounds(356, 384, 222, 19);
		panel.add(date_endtask);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("ADAM GÜN DEĞERİ:");
		lblNewLabel_3_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_3_2_1.setBounds(196, 438, 151, 16);
		panel.add(lblNewLabel_3_2_1);
		
		fld_ttime = new JTextField();
		fld_ttime.setBounds(356, 440, 222, 19);
		panel.add(fld_ttime);
		fld_ttime.setColumns(10);
		
		lblNewLabel_4 = new JLabel("(tamamlanacak/devamediyor/tamamlandı olarak giriniz.)");
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(196, 271, 274, 35);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_3 = new JLabel("GÖREV ID:");
		lblNewLabel_3_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_3_3.setBounds(588, 198, 151, 16);
		panel.add(lblNewLabel_3_3);
		
		fld_tid = new JTextField();
		fld_tid.setColumns(10);
		fld_tid.setBounds(588, 230, 156, 29);
		panel.add(fld_tid);
		btn_gata.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sdate = "";
				String edate = "";
				sdate = sdf.format(date_starttask.getDate());
				edate = sdf.format(date_endtask.getDate());
				if (fld_gad.getText().length() == 0 || fld_cid.getText().length() == 0 || fld_pnum.getText().length() == 0
						|| fld_durum.getText().length() == 0 ) {
					Helper.showMsg("empty");
				} else {
					try {
						boolean control = task.addtask(fld_gad.getText(), fld_durum.getText(), fld_cid.getText(), fld_pnum.getText(), sdate, edate);
						if (control) {
							Helper.showMsg("success");
							fld_gad.setText(null);
							fld_cid.setText(null);
							fld_durum.setText(null);
							fld_pnum.setText(null);
							date_starttask.setToolTipText(null);
							date_endtask.setToolTipText(null);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int tid = Integer.parseInt(fld_tid.getText().toString());
				
				if (tid >= 0) {
					try {
						boolean control = task.updatetaskstate(tid, fld_durum.getText());
						if (control) {
							Helper.showMsg("success");
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen durum alanını boş bırakmayınız.");
				}
			}
		});
	}
}
