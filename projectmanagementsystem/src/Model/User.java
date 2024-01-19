package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.DBConnection;
import Helper.Helper;

public class User {
	private int id;
	private String name, surname, password, username;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	public int getId() {
		return id;
	}
	public User(int id, String name, String surname, String password, String username) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.username = username;
	}
	public User () {}
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean register(String name, String surname, String password, String username) {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(name,surname,password,username) VALUES" + " (?,?,?,?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE username='" + username + "'");
			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("bu kullanıcı adına ait bir kayit bulunmaktadir");
				break;
			}
			if (!duplicate) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, surname);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, username);

				preparedStatement.executeUpdate();
				key = 1;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}
	

}
