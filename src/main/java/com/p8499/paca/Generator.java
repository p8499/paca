package com.p8499.paca;

import com.greenbird.xml.prettyprinter.PrettyPrinter;
import com.greenbird.xml.prettyprinter.PrettyPrinterFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.util.Map;

/**
 * Created by Administrator on 6/7/2018.
 */
public class Generator {
    private VelocityContext mContext;

    public Generator(Map project) {
        mContext = new VelocityContext();
        mContext.put("project", project);
    }

    public VelocityContext getContext() {
        return mContext;
    }

    private static VelocityEngine _engine;
    private static PrettyPrinter _printer;

    public static VelocityEngine getVelocityEngine() {
        if (_engine == null) {
            _engine = new VelocityEngine();
            _engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
            _engine.setProperty("classpath." + RuntimeConstants.RESOURCE_LOADER + ".class", ClasspathResourceLoader.class.getName());
            _engine.init();
        }
        return _engine;
    }

    public static PrettyPrinter getPrettyPrinter() {
        if (_printer == null) {
            PrettyPrinterFactory factory = PrettyPrinterFactory.newInstance();
            factory.setXmlDeclaration(true);
            factory.setIgnoreWhitespace(true);
            _printer = factory.newPrettyPrinter();
        }
        return _printer;
    }
}
