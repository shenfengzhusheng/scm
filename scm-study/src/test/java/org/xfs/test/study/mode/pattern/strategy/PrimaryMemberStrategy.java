package org.xfs.test.study.mode.pattern.strategy;

/**
 * 初级会员
 * 
 * @author Jeken.Liu
 *
 */
public class PrimaryMemberStrategy implements MemberStrategy {

    @Override
    public double calcPrice(double booksPrice) {
        System.out.println("对于初级会员的没有折扣");
        return booksPrice * 0.8;
    }

}
