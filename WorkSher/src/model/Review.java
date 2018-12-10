package model;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
	private long userId;
	private String username;
	private long postingId;
	private long reviewId;
	private Date reviewDate;
	private double reviewRating;
	private String reviewText;

	public Review() {
		userId = 0;
		postingId = 0;
		reviewId = 0;
		reviewDate = new Date();
		reviewRating = 0;
		reviewText = "";
	}

	public Review(long reviewId, long userId, long postingId, Date reviewDate, double reviewRating, String reviewText) {
		super();
		this.reviewId = reviewId;
		this.userId = userId;
		this.postingId = postingId;
		this.reviewDate = reviewDate;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public long getPostingId() {
		return postingId;
	}

	public void setPostingId(long postingId) {
		this.postingId = postingId;
	}

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public double getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(double reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}
