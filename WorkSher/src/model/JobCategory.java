package model;
import java.io.Serializable;

public class JobCategory {
	private String jobCategoryId;
	private String jobCategoryDesc;
	
	public JobCategory() {
	}

	public String getJobCategoryId() {
		return jobCategoryId;
	}

	public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}

	public String getJobCategoryDesc() {
		return jobCategoryDesc;
	}

	public void setJobCategoryDesc(String jobCategoryDesc) {
		this.jobCategoryDesc = jobCategoryDesc;
	}
	
}
