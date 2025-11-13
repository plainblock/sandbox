package com.github.plainblock.config;

import com.github.plainblock.task.SampleTasklet;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    private final SampleTasklet sampleTasklet;

    @Autowired
    public BatchConfig(final SampleTasklet sampleTasklet) {
        this.sampleTasklet = sampleTasklet;
    }

    @Bean
    public Job sampleTaskletJob(final JobRepository repository, final PlatformTransactionManager manager) {
        return new JobBuilder("SampleJob", repository)
//                .incrementer(new RunIdIncrementer())
                .start(sampleTaskletStep(repository, manager))
                .build();
    }

    @Bean
    public Step sampleTaskletStep(final JobRepository repository, final PlatformTransactionManager manager) {
        return new StepBuilder("SampleStep", repository)
                .tasklet(sampleTasklet, manager)
                .build();
    }

}
