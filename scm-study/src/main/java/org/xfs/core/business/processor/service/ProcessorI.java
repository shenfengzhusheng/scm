package org.xfs.core.business.processor.service;

import org.xfs.core.business.processor.model.ProcessorModel;

public interface ProcessorI {
    public ProcessorModel processor(ProcessorModel model);

    ProcessorModel test(ProcessorModel model);
}
