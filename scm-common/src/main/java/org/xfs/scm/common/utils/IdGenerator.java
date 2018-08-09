package org.xfs.scm.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * id生成器
 * 
 * @author Jeken.Liu
 *
 */
public class IdGenerator {

    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);
    @Value("${config.workerId:0}")
    private long workerId;   //机器编号

    private long workerIdBits = 8L; //机器编号位数

    @Value("${config.datacenterId:0}")
    private long datacenterId; //数据中心编号

    private long datacenterIdBits = 2L; //数据中心标示位数

    private long sequence = 0L;   //自增序列号

    private long sequenceBits = 12L; //序列号位数，默认12位

    @SuppressWarnings("unused")
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    @SuppressWarnings("unused")
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private long workerIdShift = sequenceBits; //机器节点左移位数
    private long datacenterIdShift = sequenceBits + workerIdBits; //数据中心节点左移位数
    private long sequenceMask = -1L ^ (-1L << sequenceBits); //4095
    private long lastTimestamp = -1L;//上一次生成订单id的时间

    private AtomicLong atomicSequence = new AtomicLong(0);
    /**
     * 
     * @return
     */
    public static String generator() {
        return UUID.randomUUID().toString().replace("-", "");
    }



    public String nextId() {
        long timestamp = minuteGen(); //获取当前时间串
        //Date
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一分钟内
        if (lastTimestamp != timestamp) {
            atomicSequence = new AtomicLong(0); //如果和上次生成时间不同,atomicSequence重置
        }
        lastTimestamp = timestamp;

        String suffix = this.format(atomicSequence.incrementAndGet(), "0000");
        String datePrefix = lastTimestamp + "";
        String randomSuffix = RandomUtils.getNumber(2);//两位的随机后缀

        logger.info("datePrefix:[{}],suffix:[{}],randomSuffix:[{}],长度：[{}]",
                datePrefix, suffix, randomSuffix, randomSuffix.length() + datePrefix.length() + ("" + suffix).length());
        logger.info("生成的订单id是：[{}]", datePrefix + suffix + randomSuffix);

        return datePrefix + suffix + randomSuffix;
    }

    public synchronized String nextId1() {
        long timestamp = timeGen(); //获取当前毫秒数
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp); //自旋等待到下一毫秒
            }
        } else {
            sequence = 0L; //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;


        long suffix = (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;

        String datePrefix = DateFormatUtils.format(timestamp, "yyyyMMddHHMMssSSS");

        logger.info("datePrefix:[{}],suffix:[{}],长度：[{}]", datePrefix, suffix, datePrefix.length() + ("" + suffix).length());
        logger.info("生成的订单id是：[{}]", datePrefix + suffix);

        return datePrefix + suffix;
    }


    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取分钟
     *
     * @return
     */
    protected long minuteGen() {
        Calendar calendar = Calendar.getInstance();
        String y = this.format((long) calendar.get(Calendar.YEAR), "0000");
        String mo = this.format((long) (calendar.get(Calendar.MONTH) + 1), "00");
        String d = this.format((long) calendar.get(Calendar.DAY_OF_MONTH), "00");
        String h = this.format((long) calendar.get(Calendar.HOUR_OF_DAY), "00");
        String mi = this.format((long) calendar.get(Calendar.MINUTE), "00");
        long time = new Long(y + mo + d + h + mi);
        return time;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    private String format(Long num, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(num);
    }

    public static void main(String[] args) {
        IdGenerator orderIdGenerator = new IdGenerator();
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                try {
                    Thread.sleep(50 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           System.out.println( orderIdGenerator.nextId());
        }
    }
}
