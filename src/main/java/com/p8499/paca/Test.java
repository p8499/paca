package com.p8499.paca;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.*;
import java.util.Calendar;

/**
 * Created by Administrator on 5/24/2018.
 */
public class Test {
    public static void main(String[] args) throws IOException, FormatterException {
        //we have the project
        VelocityContext ctx = new VelocityContext();
        Object project = Configuration.defaultConfiguration().jsonProvider().parse(new FileInputStream(Test.class.getResource("/project.json").getFile()), "UTF-8");
        ctx.put("Integer", Integer.class);
        ctx.put("String", String.class);
        ctx.put("Math", Math.class);
        ctx.put("Calendar", Calendar.class);
        ctx.put("JsonPath", JsonPath.class);
        ctx.put("StringUtils", StringUtils.class);
        ctx.put("project", project);
        ctx.put("index", 4);
Object x=JsonPath.parse(project).read("$.modules[2].uniques[?(@.key)].items");
        //we initialize the velocity engine
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath." + RuntimeConstants.RESOURCE_LOADER + ".class", ClasspathResourceLoader.class.getName());
        ve.init();

        //we get the template and parse the project
        Template t = ve.getTemplate("com/p8499/paca/templates/jtee/module/mapper.vm");
        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
//        System.out.println(sw.toString());
        System.out.println(new Formatter().formatSource(sw.toString()));

    }
}
