package com.p8499.paca;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

/**
 * Created by Administrator on 6/6/2018.
 */
public abstract class Generator0 extends Generator {

    public Generator0(Map project) {
        super(project);
    }

    public Generator0 writeTo(File folder) throws Exception {
        File path = getPath(folder);
        path.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(path);
        writer.write(getContent());
        writer.flush();
        writer.close();
        return this;
    }

    public abstract File getPath(File folder) throws Exception;

    public abstract String getContent() throws Exception;
}
