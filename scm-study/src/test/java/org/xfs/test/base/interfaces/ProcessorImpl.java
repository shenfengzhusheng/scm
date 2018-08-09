package org.xfs.test.base.interfaces;

import org.xfs.core.business.index.model.Person;


public class ProcessorImpl extends AbstractProcessor<Person> {



    public static void main(String[] args) {
        new ProcessorImpl().porcessor(null);
    }

    @Override
    public String doBusiness(Person t) {
        System.out.println("-----------------" + this.getClazz());
        return "success";
    }
}
