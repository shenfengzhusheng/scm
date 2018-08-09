package org.xfs.test.study.mode.pattern.command;

/**
 * 命令
 * 
 * @author Jeken.Liu
 *
 */

public class CreateCommand implements Command {
    private Receiver receiver;

    // private String state;

    public CreateCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.action();
    }

    @Override
    public void undo() {
        this.receiver.unAction();
    }

}
