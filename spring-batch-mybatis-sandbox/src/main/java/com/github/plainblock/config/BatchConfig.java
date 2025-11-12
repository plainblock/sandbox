package com.github.plainblock.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final JobLauncher launcher;
    private final JobRepository repository;
    private final PlatformTransactionManager manager;

    @Autowired
    @Qualifier("SampleTasklet")
    private Tasklet sampleTasklet;

    public BatchConfig(final JobLauncher launcher, final JobRepository repository, final PlatformTransactionManager manager) {
        this.launcher = launcher;
        this.repository = repository;
        this.manager = manager;
    }

}
