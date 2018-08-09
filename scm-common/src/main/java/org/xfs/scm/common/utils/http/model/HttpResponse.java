package org.xfs.scm.common.utils.http.model;

import java.io.Serializable;

public class HttpResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8606505126570714358L;
	private int status;
	private String response;
	private String error;
	public HttpResponse(){
		
	}
	
	public HttpResponse(int status,String response){
		this.status=status;
		this.response=response;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "HttpResponse[" +
				"status=" + status +
				", response='" + response + '\'' +
				", error='" + error + '\'' +
				']';
	}
}
