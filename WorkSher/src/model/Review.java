package model;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
	private long forUserId;
	private long fromUserId;
	private String forUsername;
	private String fromUsername;
	private long orderId;
	private long postingId;
	private long reviewId;
	private Date reviewDate;
	private double reviewRating;
	private String reviewText;
	private String reviewImgUrl;

	public Review() {
		forUserId = 0;
		fromUserId = 0;
		forUsername = "";
		fromUsername = "";
		postingId = 0;
		reviewId = 0;
		reviewDate = new Date();
		reviewRating = 1;
		reviewText = "";
		
		loadReviewImgUrl();
	}

	public Review(long reviewId, long forUserId, long fromUserId, String forUsername, String fromUsername, long orderId, long postingId, Date reviewDate, double reviewRating, String reviewText) {
		super();
		this.reviewId = reviewId;
		this.forUserId = forUserId;
		this.fromUserId = fromUserId;
		this.forUsername = forUsername;
		this.fromUsername = fromUsername;
		this.orderId = orderId;
		this.postingId = postingId;
		this.reviewDate = reviewDate;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
		
		loadReviewImgUrl();
	}
	
	private void loadReviewImgUrl() {
			if (reviewRating == 1.0) {
				reviewImgUrl = "rating1.png";
			} else if (reviewRating == 1.5) {
				reviewImgUrl = "rating1-5.png";
			} else if (reviewRating == 2.0) {
				reviewImgUrl = "rating2.png";
			} else if (reviewRating == 2.5) {
				reviewImgUrl = "rating2-5.png";
			} else if (reviewRating == 3.0) {
				reviewImgUrl = "rating3.png";
			} else if (reviewRating == 3.5) {
				reviewImgUrl = "rating3-5.png";
			} else if (reviewRating == 4.0) {
				reviewImgUrl = "rating4.png";
			} else if (reviewRating == 4.5) {
				reviewImgUrl = "rating4-5.png";
			} else {
				reviewImgUrl = "rating5.png";
			}
	}
	
	public String getReviewImgUrl() {
		return reviewImgUrl;
	}

	public long getForUserId() {
		return forUserId;
	}

	public void setForUserId(long forUserId) {
		this.forUserId = forUserId;
	}
	
	public long getFromUserId() {
		return fromUserId;
	}
	
	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	public String getForUsername() {
		return forUsername;
	}
	
	public void setForUsername(String forUsername) {
		this.forUsername = forUsername;
	}
	
	public String getFromUsername() {
		return fromUsername;
	}
	
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	
	public long getOrderId() {
		return orderId;
	}
	
	public void setOrderId(long orderId) {
		this.orderId = orderId;
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
		loadReviewImgUrl();
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	@Override
	public String toString() {
		return "(ReviewID: " + reviewId + ", ForUserID: " + forUserId + ", FromUserID: " + fromUserId + ", OrderID: " + orderId + ", PostingID: " + postingId + "ReviewText: " + reviewText + ")";
	}

}
