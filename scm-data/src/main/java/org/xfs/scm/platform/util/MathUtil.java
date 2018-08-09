package org.xfs.scm.platform.util;

import java.math.BigDecimal;

public class MathUtil {

	public static void main(String[] args) {

	}
	
	public static BigDecimal add(BigDecimal a,BigDecimal b) {
		return a.add(b);
	}
	public static BigDecimal round(BigDecimal a,int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		return a.divide(new BigDecimal(1), scale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal divide(BigDecimal a,BigDecimal b,int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
	}
    public static BigDecimal divide(int v1,int v2,int scale) {
        BigDecimal a=new BigDecimal(v1);
        BigDecimal b=new BigDecimal(v2);

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
    }
    public static BigDecimal divide(String v1,int v2,int scale) {
        BigDecimal a=new BigDecimal(v1);
        BigDecimal b=new BigDecimal(v2);

        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return a.divide(b, scale, BigDecimal.ROUND_HALF_UP);
    }



}
