package com.huike.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@ConditionalOnClass(ThreadPoolTaskScheduler.class)
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TaskSchedulingProperties.class)
@AutoConfigureAfter(TaskExecutionAutoConfiguration.class)
public class TaskSchedulingAutoConfiguration {

	//	@Bean
//	@ConditionalOnBean(name = TaskManagementConfigUtils.SCHEDULED_ANNOTATION_PROCESSOR_BEAN_NAME)
//	@ConditionalOnMissingBean({ SchedulingConfigurer.class, TaskScheduler.class, ScheduledExecutorService.class })
//	public ThreadPoolTaskScheduler taskScheduler(TaskSchedulerBuilder builder) {
//		return builder.build();
//	}
	@Bean("threadPoolTaskScheduler")
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
		executor.setThreadNamePrefix("threadPoolTaskScheduler-");
		executor.setPoolSize(20);
		//设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
		executor.setWaitForTasksToCompleteOnShutdown(true);
		//设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
		executor.setAwaitTerminationSeconds(60);
		//这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute 方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
//	@Bean
//	@ConditionalOnMissingBean
//	public TaskSchedulerBuilder taskSchedulerBuilder(TaskSchedulingProperties properties,
//			ObjectProvider<TaskSchedulerCustomizer> taskSchedulerCustomizers) {
//		TaskSchedulerBuilder builder = new TaskSchedulerBuilder();
//		builder = builder.poolSize(properties.getPool().getSize());
//		TaskSchedulingProperties.Shutdown shutdown = properties.getShutdown();
//		builder = builder.awaitTermination(shutdown.isAwaitTermination());
//		builder = builder.awaitTerminationPeriod(shutdown.getAwaitTerminationPeriod());
//		builder = builder.threadNamePrefix(properties.getThreadNamePrefix());
//		builder = builder.customizers(taskSchedulerCustomizers);
//		return builder;
//	}

}
