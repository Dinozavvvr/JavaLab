package ru.itis.javalab.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class FreemarkerMailsGeneratorImpl implements MailsGenerator {

    private final Configuration configuration;

    @Autowired
    public FreemarkerMailsGeneratorImpl(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String getMailForConfirm(String serverUrl, String confirmCode) {
        Template confirmMailTemplate;
        try {
            confirmMailTemplate = configuration.getTemplate("mail/confirm_mail.ftlh");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        Map<String, Object> map = new HashMap<>();

        map.put("server_url", serverUrl);
        map.put("confirm_code", confirmCode);

        StringWriter writer = new StringWriter();

        try {
            confirmMailTemplate.process(map, writer);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException(e);
        }
        return writer.toString();

    }
}
