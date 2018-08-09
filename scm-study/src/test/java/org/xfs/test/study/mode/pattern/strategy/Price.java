package org.xfs.test.study.mode.pattern.strategy;

public class Price {

    // 持有一个具体策略的对象
    private MemberStrategy strategy;

    public Price(MemberStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 策略方法
     */
    public double calc(double booksPrice) {
        return this.strategy.calcPrice(booksPrice);
    }
}
