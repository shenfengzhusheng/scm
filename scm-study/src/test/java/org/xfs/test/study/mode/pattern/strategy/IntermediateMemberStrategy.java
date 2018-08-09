package org.xfs.test.study.mode.pattern.strategy;

public class IntermediateMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于中级会员的折扣为30%");
        return booksPrice * 0.7;
    }

}
