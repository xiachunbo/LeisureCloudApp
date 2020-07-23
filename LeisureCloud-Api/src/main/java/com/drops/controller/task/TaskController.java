package com.drops.controller.task;

import com.drops.config.task.CronTaskRegistrar;
import com.drops.config.task.SchedulingRunnable;
import com.drops.controller.task.TaskController;
import com.drops.domain.SysJobStatus;
import com.drops.domain.job.SysJob;
import com.drops.service.ISysJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private ISysJobRepository sysJobRepository;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @ResponseBody
    @RequestMapping({"/addJob"})
    public String addJob() {
        SysJob sysJob = new SysJob();
        sysJob.setBeanName("sysServiceImpl");
        sysJob.setMethodName("job");
        sysJob.setMethodParams("111");
        sysJob.setCronExpression("0/2 * * * * ?");
        sysJob.setJobStatus(Integer.valueOf(1));
        boolean success = this.sysJobRepository.addSysJob(sysJob);
        if (!success) {
            return "111111";
        }
        if (sysJob.getJobStatus().equals(Integer.valueOf(SysJobStatus.NORMAL.ordinal()))) {
            SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            this.cronTaskRegistrar.addCronTask((Runnable) task, sysJob.getCronExpression());
        }
        return "111111111111111";
    }

    @ResponseBody
    @RequestMapping({"/updateJob"})
    public boolean updateJob() {
        SysJob sysJob = new SysJob();
        sysJob.setBeanName("sysServiceImpl");
        sysJob.setMethodName("job");
        sysJob.setCronExpression("0/2 * * * * ?");
        sysJob.setJobStatus(Integer.valueOf(0));
        SysJob existedSysJob = new SysJob();
        existedSysJob.setBeanName("sysServiceImpl");
        existedSysJob.setMethodName("job");
        existedSysJob.setCronExpression("0/5 * * * * ?");
        existedSysJob.setJobStatus(Integer.valueOf(1));
        boolean success = this.sysJobRepository.editSysJob(sysJob);
        if (!success) {
            return false;
        }

        if (existedSysJob.getJobStatus().equals(Integer.valueOf(SysJobStatus.NORMAL.ordinal()))) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            this.cronTaskRegistrar.removeCronTask((Runnable) task);
        }

        if (sysJob.getJobStatus().equals(Integer.valueOf(SysJobStatus.NORMAL.ordinal()))) {
            SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            this.cronTaskRegistrar.addCronTask((Runnable) task, sysJob.getCronExpression());
        }
        return true;
    }

    @ResponseBody
    @RequestMapping({"/deleteJob"})
    public boolean deleteJob() {
        SysJob existedSysJob = new SysJob();
        existedSysJob.setBeanName("sysServiceImpl");
        existedSysJob.setMethodName("job");
        existedSysJob.setMethodParams("111");
        existedSysJob.setCronExpression("0/2 * * * * ?");
        existedSysJob.setJobStatus(Integer.valueOf(0));
        boolean success = this.sysJobRepository.deleteSysJobById("1");
        if (!success) {
            return false;
        }
        if (existedSysJob.getJobStatus().equals(Integer.valueOf(SysJobStatus.PAUSE.ordinal()))) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            this.cronTaskRegistrar.removeCronTask((Runnable) task);
        }
        return true;
    }

    public void checkStatus() {
        SysJob existedSysJob = new SysJob();
        if (existedSysJob.getJobStatus().equals(Integer.valueOf(SysJobStatus.NORMAL.ordinal()))) {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            this.cronTaskRegistrar.addCronTask((Runnable) task, existedSysJob.getCronExpression());
        } else {
            SchedulingRunnable task = new SchedulingRunnable(existedSysJob.getBeanName(), existedSysJob.getMethodName(), existedSysJob.getMethodParams());
            this.cronTaskRegistrar.removeCronTask((Runnable) task);
        }
    }
}