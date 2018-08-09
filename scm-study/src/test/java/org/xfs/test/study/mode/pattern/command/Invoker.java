package org.xfs.test.study.mode.pattern.command;

public class Invoker {

    /**
     * 调用者持有命令对象
     */
    private Command command;

    public Command getCommand() {
        return command;
    }

    /**
     * 设置命令对象
     * 
     * @param command
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 执行命令
     */
    public void runCommand() {
        this.command.execute();
    }

    /**
     * 撤销命令
     */
    public void unDoCommand() {
        this.command.undo();
    }
}
