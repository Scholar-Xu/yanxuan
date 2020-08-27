package com.itjiguang.yanxuan.quartz.factory;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

public class CustomJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        // 使用父类的方法完成对象的创建
        Job job = super.newJob(bundle, scheduler);
        // 向创建出来的Job实例完成依赖注入
        capableBeanFactory.autowireBean(job);

        return job;
    }
}
