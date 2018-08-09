package org.xfs.scm.common.base.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Json implements Serializable {
	
	private boolean success = false;

	private String msg = "";
	
	/**
	 * "1"开头默认系统错误编码  10000 成功，101数据错误（请求），102后台异常错误，103tokenId校验失败
	 * "2"开头默认订单错误编码  10000成功，201入库订单错误，202出库订单错误，
	 * "3"开头默认库存错误编码  10000成功，301批次库存错误， 302网点库存错误
	 * "4"开头默认入库管理编码  10000成功， 401入库单错误，  402收货错误， 403上架错误
	 * "5"开头默认出库管理错误  10000成功， 501出库指令错误，502发货单错误
	 * "6"开头默认PDA系统错误    10000成功， 601配货单-按单拣货错误， 602配货单-按任务拣货错误
	 */
	
	private Integer code=10000;//100 成功101错 102 后台错误
	
	private Object data=null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

