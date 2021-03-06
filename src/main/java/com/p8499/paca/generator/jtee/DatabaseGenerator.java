package com.p8499.paca.generator.jtee;

import com.jayway.jsonpath.JsonPath;
import com.p8499.paca.Generator0;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by Administrator on 6/7/2018.
 */
public class DatabaseGenerator extends Generator0 {
    public DatabaseGenerator(Map project) {
        super(project);
        getContext().put("Integer", Integer.class);
        getContext().put("String", String.class);
        getContext().put("Math", Math.class);
        getContext().put("Calendar", Calendar.class);
        getContext().put("JsonPath", JsonPath.class);
        getContext().put("StringUtils", StringUtils.class);
        getContext().put("project", project);
    }

    @Override
    public File getPath(File folder) {
        return new File(folder, "src/main/webapp/WEB-INF/database.properties");
    }

    @Override
    public String getContent() throws IOException {
        Template template = getVelocityEngine().getTemplate("com/p8499/paca/templates/jtee/database.vm");
        Writer bufferWriter = new StringWriter();
        template.merge(getContext(), bufferWriter);
        bufferWriter.flush();
        bufferWriter.close();
        return bufferWriter.toString().trim();
    }
}
