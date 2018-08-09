package org.xfs.scm.data.business.study.threadpool.model;

import org.xfs.scm.common.utils.date.DateUtil;

public class QueryTicket extends Thread {

    // 抢票人姓名
    private String username;

    //轮序的次数
    private int loopstep=1;
    //每次间隔的时间(秒)
    private int looptime=1;

    public QueryTicket(String username,int loopstep,int looptime){
        this.loopstep=loopstep;
        this.looptime=looptime;
        this.username=username;
    }

    @Override
    public void run() {
        for (int i = 0; i < loopstep; i++) {
            //执行哪个类的哪个方法
            System.out.println("sql查询策略(执行3次每次间隔5秒)");
            System.out.println(Thread.currentThread().getName()+"["+ DateUtil.getNowTime()+"]姓名:"+username+"_第"+(i+1)+"次抢票状态:未抢到");
            try {
                Thread.sleep(looptime*1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
