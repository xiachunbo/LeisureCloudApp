package com.drops.service.impl;

import com.drops.domain.job.SysJob;
import com.drops.service.GenericService;
import com.drops.service.ISysJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysJobRepositoryImpl
        implements ISysJobRepository {
    @Autowired
    private GenericService genericService;
    public boolean addSysJob(SysJob job) {
        Map<String, Object> parems = new HashMap<>();
        parems.put("beanName",job.getBeanName());
        parems.put("methodName",job.getMethodName());
        parems.put("methodParams",job.getMethodParams());
        parems.put("cronExpression",job.getCronExpression());
        parems.put("jobStatus",job.getJobStatus());
        this.genericService.insert("sys_job",parems);
        return true;
    }


    public boolean editSysJob(SysJob job) {
        return true;
    }

    public boolean deleteSysJobById(String id) {
        Map<String, Object> parems = new HashMap<>();
        parems.put("jobId",id);
        this.genericService.deleteByParem("sys_job",parems);
        return true;
    }
}
