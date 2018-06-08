package com.p8499.paca;

import com.jayway.jsonpath.Configuration;
import com.p8499.paca.generator.jtee.*;
import com.p8499.paca.generator.jtee.base.*;
import com.p8499.paca.generator.jtee.module.*;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 6/6/2018.
 */
public class Main {
    public static void main(String[] args) {
        Options options = new Options()
                .addOption("db", false, "generate j2ee project")
                .addOption("j2ee", false, "generate j2ee project")
                .addOption("android", false, "generate j2ee project")
                .addOption("i", true, "input project file")
                .addOption("o", true, "output folder");
        try {
            CommandLine commandLine = new DefaultParser().parse(options, args);
            if (commandLine.hasOption("i") && commandLine.hasOption("o")
                    && (commandLine.hasOption("db") || commandLine.hasOption("j2ee") || commandLine.hasOption("android"))) {
                //has i and o, and has at least one of db/j2ee/android
                File inputFile = new File(commandLine.getOptionValue("i"));
                File outputFolder = new File(commandLine.getOptionValue("o"));
                FileInputStream fis = new FileInputStream(inputFile);
                Map project = (Map) Configuration.defaultConfiguration().jsonProvider().parse(fis, "UTF-8");
                fis.close();
                if (commandLine.hasOption("db"))
                    ;//generateDb(new File(commandLine.getOptionValue("i")), new File(commandLine.getOptionValue("o"), "db"));
                if (commandLine.hasOption("j2ee"))
                    generateJtee(project, new File(outputFolder, "j2ee"));
                if (commandLine.hasOption("android"))
                    ;//generateAndroid(new File(commandLine.getOptionValue("i")), new File(commandLine.getOptionValue("o"), "android"));
            } else
                verbose();
        } catch (Exception e) {
            verbose();
        }
    }

    public static void verbose() {
        System.out.println("Usage: java-jar paca.jar -db -j2ee -android -i input_file -o output_folder");
    }

    public static void generateJtee(Map project, File outputDir) throws Exception {
        new BuildGenerator(project).writeTo(outputDir);
        new WebGenerator(project).writeTo(outputDir);
        new SpringContextGenerator(project).writeTo(outputDir);
        new Log4j2Generator(project).writeTo(outputDir);
        new MybatisConfigGenerator(project).writeTo(outputDir);
        new DatabaseGenerator(project).writeTo(outputDir);
        new DefaultDateFormatterGenerator(project).writeTo(outputDir);
        new FilterExprGenerator(project).writeTo(outputDir);
        new FilterLogicExprGenerator(project).writeTo(outputDir);
        new FilterConditionExprGenerator(project).writeTo(outputDir);
        new FilterOperandExprGenerator(project).writeTo(outputDir);
        new FilterSerializerGenerator(project).writeTo(outputDir);
        new FilterDeserializerGenerator(project).writeTo(outputDir);
        new OrderByExprGenerator(project).writeTo(outputDir);
        new OrderByListExprGenerator(project).writeTo(outputDir);
        new RangeExprGenerator(project).writeTo(outputDir);
        new RangeListExprGenerator(project).writeTo(outputDir);
        BeanGenerator beanGenerator = new BeanGenerator(project);
        MaskGenerator maskGenerator = new MaskGenerator(project);
        MapperGenerator mapperGenerator = new MapperGenerator(project);
        ServiceGenerator serviceGenerator = new ServiceGenerator(project);
        ControllerBaseGenerator controllerBaseGenerator = new ControllerBaseGenerator(project);
        List modules = (List) project.get("modules");
        for (int i = 0; i < modules.size(); i++) {
            beanGenerator.writeTo(outputDir, i);
            maskGenerator.writeTo(outputDir, i);
            mapperGenerator.writeTo(outputDir, i);
            serviceGenerator.writeTo(outputDir, i);
            controllerBaseGenerator.writeTo(outputDir, i);
        }
    }
}
