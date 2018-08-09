package org.xfs.core.business.processor.service;

import org.xfs.core.business.processor.model.ProcessorModel;

public abstract class AbstractProcessor implements ProcessorI {
	@Override
	public ProcessorModel processor(ProcessorModel model) {
		model.setCode("super:"+model.getCode());
		return doBusiness(model);
	}
	public abstract ProcessorModel doBusiness(ProcessorModel model);
}
