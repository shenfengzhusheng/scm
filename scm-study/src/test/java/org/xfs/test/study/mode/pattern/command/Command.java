package org.xfs.test.study.mode.pattern.command;

/**
 * 
 * @author Jeken.Liu
 *
 */
public interface Command {

    /**
     * 执行命令
     */
    void execute();

    /**
     * 撤销命令
     */
    void undo();
}
