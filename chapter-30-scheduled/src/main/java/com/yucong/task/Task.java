package com.yucong.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
	
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void test() {
		
		System.out.println("执行定时任务");
		
	}
	
	
}
