package code_boss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
public class CodeBossApp {

    public static void main(String[] args) {
        SpringApplication.run(CodeBossApp.class, args);
    }
}
