package org.xfs.test.base.interfaces;

import java.lang.reflect.ParameterizedType;

import org.xfs.core.business.index.model.Person;

public abstract class AbstractProcessor<T> implements TestProcessor {
    private Class<T> clazz;

    public void porcessor(Person person) {
        System.out.println("===" + this.getClazz());
        try {
            doBusiness(this.getClazz().newInstance());
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public abstract String doBusiness(T t);

    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        if (clazz == null) {
            clazz = (Class<T>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        }
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
}
