package org.xfs.test.study.mode.pattern.command;

/**
 * 命令接收者,命令真正执行人
 * 
 * @author Jeken.Liu
 *
 */
public class Receiver {
    public void action() {
        System.out.println("执行命令...");
    }

    public void unAction() {
        System.out.println("撤销命令...");
    }
}
