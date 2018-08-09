package org.xfs.rpc.service.impl;

import org.xfs.rpc.service.HelloServiceI;

public class HelloServiceImpl implements HelloServiceI {

	@Override
	public String sayHi(String name) {
        return "Hi 		" + name+"!";
	}

}
