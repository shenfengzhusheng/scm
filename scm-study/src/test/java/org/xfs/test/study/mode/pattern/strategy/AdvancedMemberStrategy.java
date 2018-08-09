package org.xfs.test.study.mode.pattern.strategy;

public class AdvancedMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于高级会员的折扣为50%");
        return booksPrice * 0.5;
    }

}
