package com.liuli.aexi_android.Aexi.Control;

import com.liuli.aexi_android.Aexi.Interface.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/2/6 0006.
 */
public class CommandManager {
    private Command currentCommand;

    private List<Command> commands = new ArrayList<>();

    public void setCurrentCommand(Command currentCommand) {
        this.currentCommand = currentCommand;
    }

    public void excuteCommand() {
        if (currentCommand.excute() && currentCommand.canUndo())
            addToCommandList();
    }


    private void addToCommandList() {
        if (currentCommand.canUndo())
            commands.add(currentCommand);
    }
}
