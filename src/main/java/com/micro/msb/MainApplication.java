package com.micro.msb;

import com.micro.msb.collector.CDRCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import java.util.concurrent.Executors;

/**
 *
 * @author Yan Yan Purdiansah
 */
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class MainApplication implements CommandLineRunner {

    @Autowired
    private CDRCollector collector;
    
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        String fileDirectoryInput = "input/AIR";
        String fileDirectoryOutput = "output";
        String fileType = "-air";
        String fileExt = null;
        
        for (int i = 0; i < args.length; i++) {
            if(args[i].equalsIgnoreCase("-i")){
                i++;
                fileDirectoryInput = args[i];
            }
            else if(args[i].equalsIgnoreCase("-o")){
                i++;
                fileDirectoryOutput = args[i];                
            }
            else if(args[i].equalsIgnoreCase("-t")){
                i++;
                fileType = args[i];                
            }
            else if(args[i].equalsIgnoreCase("-x")){
                i++;
                fileExt = args[i];
            }
        }
        
        collector.setFileDirectoryInput(fileDirectoryInput);
        collector.setFileDirectoryOutput(fileDirectoryOutput);
        collector.setFileType(fileType);
        collector.setFileExt(fileExt);
        Executors.newFixedThreadPool(1).execute(collector);
    }
}
