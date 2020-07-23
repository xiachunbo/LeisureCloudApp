package com.drops.service;

import com.drops.domain.job.SysJob;

public interface ISysJobRepository {
  boolean addSysJob(SysJob paramSysJob);
  
  boolean editSysJob(SysJob paramSysJob);
  
  boolean deleteSysJobById(String paramString);
}