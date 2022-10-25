package org.example.restservice;

import java.io.File;
import java.util.Scanner;

public class Job {
    private final long job_id;

    public Job(long job_id){
        this.job_id = job_id;
    }

    public long getJob_id(){
        return this.job_id;
    }
}
