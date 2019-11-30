package com.example;

import java.io.File;
import java.util.concurrent.Callable;

import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;

@Command(name = "app", description = "...",
        mixinStandardHelpOptions = true)
public class AppCommand implements Callable<Integer> {

    @Mixin
    private Options options;

    static class Options {
        @Option(names = {"-v", "--verbose"}, description = "...")
        boolean verbose;
        @Option(names = {"-a"}, split = ",")
        String[] array = {};
        @Option(names = {"-f"})
        File file;
    }

    public static void main(String[] args) throws Exception {
        //PicocliRunner.run(AppCommand.class, args);
        int exitStatus = PicocliRunner.execute(AppCommand.class, args);
        System.exit(exitStatus);
    }

    public void run() {
        // business logic here
        if (options.verbose) {
            System.out.println("Hi!");
        }
    }

    @Override
    public Integer call() throws Exception {
        return 0;
    }

}
