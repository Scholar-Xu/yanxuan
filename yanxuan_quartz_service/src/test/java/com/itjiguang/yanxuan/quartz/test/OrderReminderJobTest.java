package com.itjiguang.yanxuan.quartz.test;

import com.itjiguang.yanxuan.quartz.job.OrderReminderJob;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;

public class OrderReminderJobTest {

    /**
     * 实际情况：
     *  1. 在订单确认时动态添加trigger
     *  2. 在订单支付的时候移除trigger
     *
     * 动态创建触发器
     *  1. 触发器关联JobDetail
     *  2. 触发器中传递参数
     */
    @Test
    public void testOrderReminder()throws Exception{
        // 订单号、手机号
        String phoneNum = "13112344321";
        String orderNo = "996633225588" ;
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, 10);
        Date startTime = calendar.getTime();

        // 获取调度器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // 获取JobDetail
        JobDetail jobDetail = null;
        JobKey jobKey = new JobKey("order_reminder_job", "order");
        if(scheduler.checkExists(jobKey)){
            jobDetail = scheduler.getJobDetail(jobKey);
        }else{
            jobDetail = JobBuilder.newJob(OrderReminderJob.class).withIdentity(jobKey).storeDurably().build();
        }

        // 动态创建Trigger
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("order_reminder_trigger_" + phoneNum, "order")
                .forJob(jobDetail)
                .usingJobData("phoneNum", phoneNum).usingJobData("orderNo", orderNo)
                .startAt(startTime)
                .build();

        scheduler.scheduleJob(trigger);
    }

}
