package org.xfs.scm.common.exception;

import org.xfs.scm.common.utils.json.FastJsonUtil;

public class SqlException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5383186791371166046L;

	public SqlException(String message) {
		super(message,"10011");
	}
	public SqlException(String module,String message,Object[]objects) {
		super(module,"10011",objects,message);



	}


}
