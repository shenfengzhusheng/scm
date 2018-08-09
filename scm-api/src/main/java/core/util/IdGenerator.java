package core.util;

import java.util.UUID;

/**
 * id生成器
 * 
 * @author Jeken.Liu
 *
 */
public class IdGenerator {
    /**
     * 
     * @return
     */
    public static String generator() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
