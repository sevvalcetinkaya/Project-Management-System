package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Projection;

import Helper.DBConnection;
import Helper.Helper;
import Model.Project;
import Model.Task;
import Model.User;
import Model.Worker;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;

public class projectGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	static Project pro = new Project();
	static User user = new User();
	static Worker worker = new Worker();
	static Task task = new Task();
	private JTextField fld_pname;
	private JTable table_project;
	private DefaultTableModel projectmodel = null;
	private Object[] projectdata = null;
	private DBConnection conn = new DBConnection();
	private JTextField fld_pid;
	private JTextField fld_wid;
	private DefaultTableModel workermodel = null;
	private Object[] workerdata = null;
	private DefaultTableModel taskmodel = null;
	private Object[] taskdata = null;
	private JTable table_worker;
	private JTable table_task;
	private JTextField fld_cadi;
	private JTextField fld_csoyadi;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					projectGUI frame = new projectGUI(pro);
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
	public projectGUI(Project pro) throws SQLException {
		projectmodel = new DefaultTableModel();
		Object[] colProjectname = new Object[4];

		colProjectname[0] = "ID";
		colProjectname[1] = "Ad";
		colProjectname[2] = "Başlangıç Tarihi";
		colProjectname[3] = "Bitiş Tarihi";
		projectmodel.setColumnIdentifiers(colProjectname);
		projectdata = new Object[4];
		for (int i = 0; i < pro.getProjectList().size(); i++) {
			projectdata[0] = pro.getProjectList().get(i).getId();
			projectdata[1] = pro.getProjectList().get(i).getName();
			projectdata[2] = pro.getProjectList().get(i).getStartdate();
			projectdata[3] = pro.getProjectList().get(i).getEnddate();
			projectmodel.addRow(projectdata);
		}
		workermodel = new DefaultTableModel();
		Object[] colworkername = new Object[3];
		colworkername[0] = "ID";
		colworkername[1] = "İsim";
		colworkername[2] = "Soyisim";
		workermodel.setColumnIdentifiers(colworkername);
		workerdata = new Object[3];
		for (int i = 0; i < worker.getWorkerList().size(); i++) {
			workerdata[0] = worker.getWorkerList().get(i).getId();
			workerdata[1] = worker.getWorkerList().get(i).getName();
			workerdata[2] = worker.getWorkerList().get(i).getSurname();
			workermodel.addRow(workerdata);
		}
		DefaultTableModel taskmodel = new DefaultTableModel();
		Object[] coltaskname = new Object[5];
		coltaskname[0] = "ID";
		coltaskname[1] = "Ad";
		coltaskname[2] = "Durum";
		coltaskname[3] = "Tamamladığı Görevler";
		coltaskname[4] = "Tamamlamadığı Görevler";
		taskmodel.setColumnIdentifiers(coltaskname);
		Object[] taskdata = new Object[5];

		setTitle("Proje Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(176, 196, 222));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOŞGELDİNİZ");
		lblNewLabel.setBounds(10, 30, 213, 24);
		lblNewLabel.setForeground(new Color(119, 136, 153));
		lblNewLabel.setBackground(new Color(240, 255, 255));
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		w_pane.add(lblNewLabel);
		
		JButton btn_pcıkıs = new JButton("ÇIKIŞ");
		btn_pcıkıs.setBounds(669, 701, 85, 29);
		btn_pcıkıs.setBackground(new Color(255, 255, 255));
		btn_pcıkıs.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_pcıkıs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginGUI login=new loginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		w_pane.add(btn_pcıkıs);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 64, 744, 627);
		w_pane.add(w_tab);
		
		JPanel w_project = new JPanel();
		w_project.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Projeler", null, w_project, null);
		w_project.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Proje Adı:");
		lblNewLabel_1.setBounds(512, 42, 206, 28);
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		w_project.add(lblNewLabel_1);
		
		fld_pname = new JTextField();
		fld_pname.setBounds(512, 80, 206, 19);
		w_project.add(fld_pname);
		fld_pname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Proje Başlangıç Tarihi:");
		lblNewLabel_2.setBounds(512, 128, 206, 25);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		w_project.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Proje Bitiş Tarihi:");
		lblNewLabel_3.setBounds(512, 217, 206, 26);
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		w_project.add(lblNewLabel_3);
		
		JDateChooser select_sdate = new JDateChooser();
		select_sdate.setBounds(512, 163, 206, 19);
		w_project.add(select_sdate);
		
		JDateChooser select_edate = new JDateChooser();
		select_edate.setBounds(512, 267, 206, 19);
		w_project.add(select_edate);
		
		JButton btn_pekle = new JButton("PROJE EKLE");
		btn_pekle.setBounds(512, 330, 206, 28);
		btn_pekle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		btn_pekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String sdate = "";
				String edate = "";
				try {
					sdate = sdf.format(select_sdate.getDate());
					edate = sdf.format(select_edate.getDate());
				} catch (Exception e2) {
					e2.printStackTrace();
				}

				if (sdate.length() == 0 || edate.length() == 0) {
					Helper.showMsg("Lutfen gecerli bir tarih girin");

				} else {
					try {
						boolean control = pro.addProject(fld_pname.getText(), sdate, edate);
						if (control) {
							Helper.showMsg("success");
							updateprojectmodel();

						} else {
							Helper.showMsg("error");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		w_project.add(btn_pekle);
		
		JScrollPane w_scrollproject = new JScrollPane();
		w_scrollproject.setBounds(10, 10, 474, 580);
		w_project.add(w_scrollproject);
		
		table_project = new JTable(projectmodel);
		w_scrollproject.setViewportView(table_project);
		table_project.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged (ListSelectionEvent e) {
				fld_pid.setText(table_project.getValueAt(table_project.getSelectedRow(), 0).toString());
			}
		});
		
		table_project.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectid = Integer.parseInt(table_project.getValueAt(table_project.getSelectedRow(), 0).toString());
					String selectname = table_project.getValueAt(table_project.getSelectedRow(), 1).toString();
					String selectstartdate = table_project.getValueAt(table_project.getSelectedRow(), 2).toString();
					String selectenddate =  table_project.getValueAt(table_project.getSelectedRow(), 3).toString();
					try {
						boolean control = pro.updateproject(selectid, selectname, selectstartdate, selectenddate);
						if (control) {
							Helper.showMsg("success");
							updateprojectmodel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Proje ID:");
		lblNewLabel_4.setBounds(512, 413, 111, 19);
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		w_project.add(lblNewLabel_4);
		
		fld_pid = new JTextField();
		fld_pid.setBounds(512, 442, 217, 19);
		w_project.add(fld_pid);
		fld_pid.setColumns(10);
		
		JButton btn_tekle = new JButton("GÖREV EKLE");
		btn_tekle.setBounds(511, 510, 110, 34);
		btn_tekle.setForeground(new Color(255, 255, 255));
		btn_tekle.setBackground(new Color(176, 196, 222));
		btn_tekle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btn_tekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lblNewLabel_4.getText().length() == 0) {
					Helper.showMsg("empty");
				} else {
					boolean key = true;
					try {
						Task tsk = new Task();
						taskGUI tGUI = new taskGUI(tsk);
						tGUI.setVisible(true);
						dispose();
						key = false;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (key) Helper.showMsg("Proje seçiniz.");
				} 
			}
		});
		w_project.add(btn_tekle);
		
		JButton btnNewButton_4 = new JButton("GÜNCELLE");
		btnNewButton_4.setBounds(631, 510, 98, 34);
		btnNewButton_4.setForeground(new Color(255, 255, 255));
		btnNewButton_4.setBackground(new Color(176, 196, 222));
		btnNewButton_4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selrow = table_project.getSelectedRow();
				if (selrow >= 0) {
					String selproject = table_project.getModel().getValueAt(selrow, 0).toString();
					int selproid = Integer.parseInt(selproject);
					try {
						boolean control = pro.updateprojectdate(selproid, select_edate.getDateFormatString());
						if (control) {
							Helper.showMsg("success");
							updateprojectmodel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen proje seçiniz.");
				}
			}
		});
		w_project.add(btnNewButton_4);
		
		JPanel w_worker = new JPanel();
		w_worker.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Çalışanlar", null, w_worker, null);
		w_worker.setLayout(null);
		
		JScrollPane w_scrollworker = new JScrollPane();
		w_scrollworker.setBounds(10, 26, 208, 214);
		w_worker.add(w_scrollworker);
		
		table_worker = new JTable(workermodel);
		w_scrollworker.setViewportView(table_worker);
		table_worker.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged (ListSelectionEvent e) {
				fld_wid.setText(table_worker.getValueAt(table_worker.getSelectedRow(), 0).toString());
			}
		});

		table_worker.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectid = Integer.parseInt(table_worker.getValueAt(table_worker.getSelectedRow(), 0).toString());
					String selectname = table_worker.getValueAt(table_worker.getSelectedRow(), 1).toString();
					String selectsurname = table_worker.getValueAt(table_worker.getSelectedRow(), 2).toString();
					try {
						boolean control = worker.updateWorker(selectid, selectname, selectsurname);
						if (control) {
							Helper.showMsg("success");
							updateworkermodel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("ÇALIŞAN ID:");
		lblNewLabel_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_5.setBounds(10, 461, 96, 22);
		w_worker.add(lblNewLabel_5);
		
		fld_wid = new JTextField();
		fld_wid.setBounds(108, 466, 69, 19);
		w_worker.add(fld_wid);
		fld_wid.setColumns(10);
		
		JButton btnNewButton = new JButton("SEÇ");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(176, 196, 222));
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_worker.getSelectedRow();
				if (selRow >= 0) {
					String selworker = table_worker.getModel().getValueAt(selRow, 0).toString();
					int selworkerid = Integer.parseInt(selworker);
					DefaultTableModel clearModel = (DefaultTableModel) table_task.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < task.getWorkerTaskList(selworkerid).size(); i++) {
							taskdata[0] = task.getWorkerTaskList(selworkerid).get(i).getId();
							taskdata[1] = task.getWorkerTaskList(selworkerid).get(i).getName();
							taskdata[2] = task.getWorkerTaskList(selworkerid).get(i).getState();
							taskdata[3] = worker.getcomptasklist(selworkerid);
							taskdata[4] = worker.getuncomptasklist(selworkerid);
							taskmodel.addRow(taskdata);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_task.setModel(taskmodel);

				} else {
					Helper.showMsg("Lütfen bir çalışan seçin");

				}
			}
		});
		btnNewButton.setBounds(10, 497, 69, 29);
		w_worker.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(241, 26, 488, 535);
		w_worker.add(scrollPane);
		
		table_task = new JTable();
		scrollPane.setViewportView(table_task);
		
		JLabel lblNewLabel_6 = new JLabel("Çalışan Adı:");
		lblNewLabel_6.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_6.setBounds(10, 270, 106, 22);
		w_worker.add(lblNewLabel_6);
		
		fld_cadi = new JTextField();
		fld_cadi.setBounds(10, 302, 167, 19);
		w_worker.add(fld_cadi);
		fld_cadi.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Çalışan Soyadı:");
		lblNewLabel_7.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 15));
		lblNewLabel_7.setBounds(10, 331, 123, 22);
		w_worker.add(lblNewLabel_7);
		
		fld_csoyadi = new JTextField();
		fld_csoyadi.setBounds(10, 363, 167, 19);
		w_worker.add(fld_csoyadi);
		fld_csoyadi.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("EKLE");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(176, 196, 222));
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (fld_cadi.getText().length() == 0 || fld_csoyadi.getText().length() == 0) {
						Helper.showMsg("empty");
					} else {
						try {
							boolean control = worker.addWorker(fld_cadi.getText(), fld_csoyadi.getText());
							if (control) {
								Helper.showMsg("success");
								fld_cadi.setText(null);
								fld_csoyadi.setText(null);
								updateworkermodel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				}
		});
		btnNewButton_1.setBounds(10, 397, 69, 29);
		w_worker.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("SİL");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(176, 196, 222));
		btnNewButton_2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (fld_wid.getText().length() == 0) {
						Helper.showMsg("Lutfen gecerli bir çalışan seciniz");

					} else {
						if (Helper.confirm("sure")) {

							int selectID = Integer.parseInt(fld_wid.getText());
							try {
								boolean control = worker.deleteWorker(selectID);
								if (control) {
									Helper.showMsg("success");
									fld_wid.setText(null);
									updateworkermodel();
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}

					}
				}
		});
		btnNewButton_2.setBounds(108, 497, 69, 29);
		w_worker.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("GÜNCELLE");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(176, 196, 222));
		btnNewButton_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_wid.getText().length() == 0) {
					Helper.showMsg("Lutfen geçerli bir çalışan seçiniz.");
				} else {
					int selectid = Integer.parseInt(fld_wid.getText());
					try {
						boolean control = worker.updateWorker(selectid, fld_cadi.getText(), fld_csoyadi.getText());
						if(control) {
							Helper.showMsg("success");
							fld_wid.setText(null);
							updateworkermodel();
						}
					} catch(SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		btnNewButton_3.setBounds(86, 397, 91, 29);
		w_worker.add(btnNewButton_3);
	}
	public void updateprojectmodel () throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_project.getModel();
		clearModel.setRowCount(0);
		int i = 0;
			for (; i < pro.getProjectList().size(); i++) {
			projectdata[0] = pro.getProjectList().get(i).getId();
			projectdata[1] = pro.getProjectList().get(i).getName();
			projectdata[2] = pro.getProjectList().get(i).getStartdate();
			projectdata[3] = pro.getProjectList().get(i).getEnddate();
			projectmodel.addRow(projectdata);
		}
	}
	public void updateworkermodel () throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
		clearModel.setRowCount(0);
		int i = 0;
			for (;i < worker.getWorkerList().size(); i++) {
			workerdata[0] = worker.getWorkerList().get(i).getId();
			workerdata[1] = worker.getWorkerList().get(i).getName();
			workerdata[2] = worker.getWorkerList().get(i).getSurname();
			workermodel.addRow(workerdata);
		}
	}
}
