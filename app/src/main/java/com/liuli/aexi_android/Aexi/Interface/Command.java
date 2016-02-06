package com.liuli.aexi_android.Aexi.Interface;

/**
 * Created by Administrator on 2016/2/6 0006.
 */
public interface Command {
    public boolean excute();

    public void unExcute();

    public boolean canUndo();
}
