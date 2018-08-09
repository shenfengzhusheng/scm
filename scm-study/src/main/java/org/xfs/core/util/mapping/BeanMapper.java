package org.xfs.core.util.mapping;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;

public class BeanMapper {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BeanMapper.class);

    public static <T> List<T> mapAsList(Iterable<?> sources, Class<T> destinationClass, DozerBeanMapper mapper) {

        ArrayList<T> targets = new ArrayList<T>();
        for (Object source : sources) {
            targets.add(mapper.map(source, destinationClass));
        }
        return targets;
    }

    public static <T> T mapping(Object source, Class<T> destinationClass, DozerBeanMapper mapper) {
        T dest = null;
        try {
            dest = mapper.map(source, destinationClass);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return dest;
    }
}
