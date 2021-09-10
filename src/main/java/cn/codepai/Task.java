package cn.codepai;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 半小时执行一次
 */
public class Task {

    public static void startTask(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                DomainUpdater.updateRecordInfo(IPAddrUtil.getV4IP());
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,1000*60*30);
        System.out.println("定时任务启动成功！");
    }
}
