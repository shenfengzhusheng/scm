package org.xfs.test.study.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.Base64Utils;

public class Base64UtilsTest {
    @Test
    public void test() {
        // 需要Java 8 或 Apache Commons Codec
        String str = "123";
        String str2 = new String(Base64Utils.decodeFromString(Base64Utils.encodeToString(str.getBytes())));
        Assert.assertEquals(str, str2);
    }
}
