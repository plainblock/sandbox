package com.github.plainblock.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component("SampleTasklet")
@StepScope
public class SampleTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext context) throws Exception {
        System.out.println("SampleTasklet");
        return RepeatStatus.FINISHED;
    }

}
