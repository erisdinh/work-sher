package model;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable{
	
	private int orderid;
	private User requestUser;
	private User postUser;
	private Posting posting;
	private String description;
	private Date dateRequested;
	private Date dateResponsed;
	private Date dateCompleted;
	private String status;
	
	public Order() {}
	
	public Order(int orderid, User requestUser, User postUser, Posting posting, Date dateRequested, Date dateResponsed, Date dateCompleted, String status) {
		this.orderid = orderid;
		this.requestUser = requestUser;
		this.postUser = postUser;
		this.posting = posting;
		this.dateRequested = dateRequested;
		this.dateResponsed = dateResponsed;
		this.dateCompleted = dateCompleted;
		this.status = status;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public User getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(User requestUser) {
		this.requestUser = requestUser;
	}

	public User getPostUser() {
		return postUser;
	}

	public void setPostUser(User postUser) {
		this.postUser = postUser;
	}

	public Posting getPosting() {
		return posting;
	}

	public void setPosting(Posting posting) {
		this.posting = posting;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateRequested() {
		return dateRequested;
	}

	public void setDateRequested(Date dateRequested) {
		this.dateRequested = dateRequested;
	}

	public Date getDateResponsed() {
		return dateResponsed;
	}

	public void setDateResponsed(Date dateResponsed) {
		this.dateResponsed = dateResponsed;
	}

	public Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
