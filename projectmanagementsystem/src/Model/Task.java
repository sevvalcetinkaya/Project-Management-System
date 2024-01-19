package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Task {
	private int id, workerid, projectid;
	private String name, state, taskstartdate, taskenddate;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Task() {}

	public Task(int id, String name, String state, int workerid, int projectid, String taskstartdate, String taskenddate) {
		this.id = id;
		this.workerid = workerid;
		this.projectid = projectid;
		this.name = name;
		this.state = state;
		this.taskstartdate = taskstartdate;
		this.taskenddate = taskenddate;
	}
	
	public boolean addtask (String name, String state, String workerid, String projectid, String taskstartdate, String taskenddate) throws SQLException {
		String query = "INSERT INTO task " + "(name,state,workerid,projectid,taskstartdate,taskenddate) VALUES" + "(?,?,?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, state);
			preparedStatement.setString(3, workerid);
			preparedStatement.setString(4, projectid);
			preparedStatement.setString(5, taskstartdate);
			preparedStatement.setString(6, taskenddate);
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
	
	public boolean updatetask (int id, String name, String state, String workerid, String projectid, String taskstartdate, String taskenddate) throws SQLException {
		 String query = "UPDATE task SET name=?, state=?, workerid=?, projectid=?, taskstartdate=?, taskenddate=? WHERE id=?";
			boolean key = false;
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, state);
				preparedStatement.setString(3, workerid);
				preparedStatement.setString(4, projectid);
				preparedStatement.setString(5, taskstartdate);
				preparedStatement.setString(6, taskenddate);
				preparedStatement.setInt(7, id);

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
	
	public boolean updatetaskstate (int id, String  state) throws SQLException {
		String query = "UPDATE task SET state=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, state);
			preparedStatement.setInt(2, id);
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
	
	public ArrayList<Task> getWorkerTaskList(int workerid) throws SQLException {
		ArrayList<Task> list = new ArrayList<>();

		Task obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT t.id,t.name,t.state,t.workerid, t.projectid, t.taskstartdate, t.taskenddate FROM task t LEFT JOIN worker w ON t.id=w.taskid WHERE workerid= "
							+ workerid);

			while (rs.next()) {
				obj = new Task(rs.getInt("t.id"), rs.getString("t.name"), rs.getString("t.state"),
						rs.getInt("t.workerid"), rs.getInt("t.projectid"), rs.getString("t.taskstartdate"), rs.getString("taskenddate"));
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

	public int getWorkerid() {
		return workerid;
	}

	public void setWorkerid(int workerid) {
		this.workerid = workerid;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getTaskstartdate() {
		return taskstartdate;
	}
	
	public void setTaskstartdate(String taskstartdate) {
		this.taskstartdate = taskstartdate;
	}
	
	public String getTaskenddate() {
		return taskenddate;
	}
	
	public void setTaskenddate(String taskenddate) {
		this.taskenddate = taskenddate;
	}

}
