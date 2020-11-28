package GUI_Server;

import javafx.util.converter.DateTimeStringConverter;

public class User {
	private int Id;
	private String name;
	private String pass;
	private String Date;
	private int gender;
	private String room;
	public User() {
		super();
	}
	public User(int id, String name, String pass, String date, int gender,
			String room) {
		super();
		Id = id;
		this.name = name;
		this.pass = pass;
		Date = date;
		this.gender = gender;
		this.room = room;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String toString() {
		return "User [Id=" + Id + ", name=" + name + ", pass=" + pass
				+ ", Date=" + Date + ", gender=" + gender + ", room=" + room
				+ "]";
	}
	

}
