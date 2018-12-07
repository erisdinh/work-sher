package model;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
	private long userId;
	private long postingId;
	private long reviewId;
	private Date reviewDate;
	private int reviewRating;
	private String reviewText;

	public Review() {
		userId = 0;
		postingId = 0;
		reviewId = 0;
		reviewDate = new Date();
		reviewRating = 0;
		reviewText = "";
	}

	public Review(long reviewId, long userId, long postingId, Date reviewDate, int reviewRating, String reviewText) {
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

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}
