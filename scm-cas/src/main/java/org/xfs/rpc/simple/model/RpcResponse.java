package org.xfs.rpc.simple.model;

/**
 * RPC响应
 * @author xixingyingzhongdui
 *
 */
public class RpcResponse {
	private String reqeustId;
	private Throwable error;
	private Object result;
	public String getReqeustId() {
		return reqeustId;
	}
	public void setReqeustId(String reqeustId) {
		this.reqeustId = reqeustId;
	}
	public Throwable getError() {
		return error;
	}
	public void setError(Throwable error) {
		this.error = error;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}

}
