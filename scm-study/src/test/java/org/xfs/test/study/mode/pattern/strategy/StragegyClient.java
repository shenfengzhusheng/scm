package org.xfs.test.study.mode.pattern.strategy;

public class StragegyClient {

    public static void main(String[] args) {
        // 选择并创建需要使用的策略对象
        MemberStrategy strategy = new VIPMemberStrategy();
        // 创建环境
        Price price = new Price(strategy);
        // 计算价格
        double quote = price.calc(300);
        System.out.println("图书的最终价格为：" + quote);
    }

}
