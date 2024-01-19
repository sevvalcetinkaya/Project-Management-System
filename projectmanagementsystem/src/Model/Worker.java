package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Worker {
	private int id;
	private int taskid;
	private String name, surname; 
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	public Worker(int id, String name, String surname, int  taskid) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.taskid = taskid;
	}
	
	public Worker () {}
	
	public ArrayList<Worker> getWorkerList() throws SQLException {
		ArrayList<Worker> list = new ArrayList<>();
		Worker wr;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT *FROM worker");
		while (rs.next()) {
				wr = new Worker(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getInt("taskid"));
				list.add(wr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public boolean addWorker (String name, String surname) throws SQLException {
		String query = "INSERT INTO worker " + "(name,surname) VALUES" + "(?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	
	public boolean deleteWorker(int id) throws SQLException {
		String query = "DELETE FROM worker WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	
 	public boolean updateWorker (int id, String name, String surname) throws SQLException {
		 String query = "UPDATE worker SET name=?, surname=? WHERE id=?";
			boolean key = false;
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, surname);
				preparedStatement.setInt(3, id);
				preparedStatement.executeUpdate();
				key = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (key)
				return true;
			else
				return false;
	 }
 	
 	public ArrayList<Integer> getcomptasklist(int workerid) throws SQLException {
		ArrayList<Integer> list = new ArrayList<>();

		int obj;
		try {
			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT COUNT(*) FROM task t LEFT JOIN worker w ON t.id=w.taskid WHERE workerid= "
							+ workerid + " AND state='tamamlandı' GROUP BY t.state ");

			while (rs.next()) {
				obj = rs.getInt("COUNT(*)");
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
 	
 	public ArrayList<Integer> getuncomptasklist(int workerid) throws SQLException {
 		ArrayList<Integer> list = new ArrayList<>();
 		int obj;
 		try {
 			Connection con = conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT COUNT(*) FROM task t LEFT JOIN worker w ON t.id=w.taskid WHERE workerid= "
			+ workerid + " AND state='tamamlanacak' OR state='devamediyor' GROUP BY t.state");

			while (rs.next()) {
				obj = rs.getInt("COUNT(*)");
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
 	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	
}
