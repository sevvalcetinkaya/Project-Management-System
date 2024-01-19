package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Project {
	private int id;
	private String name, startdate, enddate;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	public Project(int id, String name, String startdate, String enddate) {
		this.id = id;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
	
	}
	public Project () {}
	
	public ArrayList<Project> getProjectList() throws SQLException {
		ArrayList<Project> list = new ArrayList<>();
		Project obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM project");
			while (rs.next()) {
				obj = new Project(rs.getInt("id"), rs.getString("name"), rs.getString("startdate"), rs.getString("enddate"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
			return list;
		}
	 public boolean addProject ( String name, String startdate, String enddate) throws SQLException {
		 String query = "INSERT INTO project " + "(name,startdate,enddate) VALUES" + "(?,?,?)";
			boolean key = false;
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, startdate);
				preparedStatement.setString(3, enddate);
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
	 public boolean updateproject (int id, String name, String startdate, String enddate) throws SQLException {
		 String query = "UPDATE project SET name=?,startdate=?,enddate=? WHERE id=?";
			boolean key = false;
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, startdate);
				preparedStatement.setString(3, enddate);
				preparedStatement.setInt(4, id);

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
	 
	 public boolean updateprojectdate (int id, String  enddate) throws SQLException {
			String query = "UPDATE project SET enddate=? WHERE id=?";
			boolean key = false;
			try {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, enddate);
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
}
