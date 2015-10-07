package chapter07.reflect.editor;

import java.util.Date;

public class NodeDO {

	private String name;
	
	private String email;
	
	private Date dateTime;
	
	public String toString() {
		return name + "\t" + email + "\t" + getDateTimeString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateTime() {
		return dateTime;
	}
	
	public String getDateTimeString() {
		return NodeDOEditor.DEFAULT_DATE_FORMAT.format(dateTime);
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
