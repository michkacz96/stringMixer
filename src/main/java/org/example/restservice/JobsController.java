package org.example.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.example.stringMixer.Main;
import org.example.stringMixer.MySQLCon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import static org.example.stringMixer.Main.generate_strings;

@RestController
public class JobsController {

    private final AtomicLong count = new AtomicLong();

    /**
     * return number of jobs
     */
    @GetMapping("/count-jobs")
    public Job get_count_jobs() {
        return new Job(this.count.incrementAndGet());
    }

    /**
     * return job by id
     * @param id
     */
    @GetMapping("/get-job")
    public String get_job_by_id(int id) {
        return MySQLCon.get_job(id);
    }

    /**
     * @param min - minimal number of characters in a string
     * @param max - maximal number of characters in a string
     * @param quantity - number of generated strings
     * @param av_chars - available characters
     */
    @PostMapping("/generate")
    public void generate(int min, int max, int quantity, char[] av_chars){
        generate_strings(min, max, quantity, av_chars);
    }
}