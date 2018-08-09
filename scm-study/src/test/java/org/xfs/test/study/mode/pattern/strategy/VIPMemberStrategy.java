package org.xfs.test.study.mode.pattern.strategy;

public class VIPMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于VIP会员的折扣7折");
        return booksPrice * 0.3;
    }

}
