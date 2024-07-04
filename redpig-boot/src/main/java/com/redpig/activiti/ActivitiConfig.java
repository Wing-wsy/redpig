package com.redpig.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wing
 * @create 2024/7/4
 */
@Configuration
public class ActivitiConfig implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Value("${spring.datasource.driver-class-name}")
    String driver;

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    @Bean
    public ProcessEngine processEngine() {
        ProcessEngineConfiguration config = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration();

        // 连接数据库的配置
        config.setJdbcDriver(driver);
        config.setJdbcUrl(url);
        config.setJdbcUsername(username);
        config.setJdbcPassword(password);

        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //工作流的核心对象，ProcessEnginee对象
        ProcessEngine processEngine = config.buildProcessEngine();
        return processEngine;
    }

    @Bean
    public TaskService taskService() {
        ProcessEngine processEngine = processEngine();
        TaskService taskService = processEngine.getTaskService();
        return taskService;
    }

    @Bean
    public HistoryService historyService() {
        ProcessEngine processEngine = processEngine();
        HistoryService historyService = processEngine.getHistoryService();
        return historyService;
    }

    @Bean
    public RepositoryService repositoryService() {
        ProcessEngine processEngine = processEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        return repositoryService;
    }

    @Bean
    public RuntimeService runtimeService() {
        ProcessEngine processEngine = processEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        return runtimeService;
    }
}
