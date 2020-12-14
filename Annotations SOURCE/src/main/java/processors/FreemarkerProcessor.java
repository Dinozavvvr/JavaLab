package processors;

import annotations.HtmlForm;
import annotations.HtmlInput;
import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * FreemarkerProcessor
 * created: 09-12-2020 - 21:13
 * project: Annotations SOURCE
 *
 * @author dinar
 * @version v0.1
 */

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"annotations.HtmlForm"})
public class FreemarkerProcessor extends AbstractProcessor {

    private final String RESOURCES = "src/main/resources/";

    private final String HTML_SUFFIX = ".html";

    private final String FTLH_SUFFIX = ".ftlh";

    private File file;

    private Configuration configuration = new Configuration(Configuration.VERSION_2_3_20);

    private Template template;

    private final String METHOD = "method";

    private final String ACTION = "action";

    private final String INPUTS = "inputs";

    private final String HTML_INPUT = HtmlInput.class.getSimpleName();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            configuration.setTemplateLoader(new FileTemplateLoader(new File(RESOURCES)));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Set<? extends Element> elements = roundEnv
                .getElementsAnnotatedWith(HtmlForm.class);

        for (Element element : elements) {
            HtmlForm form = element.getAnnotation(HtmlForm.class);
            Map<String, Object> attributes = new HashMap<>();
            List<Input> inputs = new ArrayList<>();

            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName() + HTML_SUFFIX;
            Path out = Paths.get(path);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()))) {
                template = configuration.getTemplate(element.getSimpleName() + FTLH_SUFFIX);

                List<? extends Element> enclosedElements = element.getEnclosedElements();

                for (Element enclosedElement : enclosedElements) {
                    ElementKind elementKind = enclosedElement.getKind();

                    if (elementKind.isField()) {
                        List<? extends AnnotationMirror> annotationMirrors =
                                enclosedElement.getAnnotationMirrors();

                        for (AnnotationMirror mirror : annotationMirrors) {
                            Element elem = mirror.getAnnotationType().asElement();

                            if (elem.getSimpleName().contentEquals(HTML_INPUT)) {
                                HtmlInput inputAnnotation = enclosedElement.getAnnotation(HtmlInput.class);
                                Input input = Input.builder()
                                        .name(inputAnnotation.name())
                                        .type(inputAnnotation.type())
                                        .placeholder(inputAnnotation.placeholder())
                                        .build();

                                inputs.add(input);
                                break;
                            }
                        }
                    }
                }

                attributes.put(METHOD, form.method());
                attributes.put(ACTION, form.action());
                attributes.put(INPUTS, inputs);

                template.process(attributes, writer);
                writer.flush();
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}


/* -------------------------------------------- deprecated --------------------------------------------*/

/*    *//* alternate path *//*
    String filePath = PATH + element.getSimpleName() + HTML_SUFFIX;
        file = new File(filePath);
*/

/* использую это потому что пробелы в пути, потом поменяю *//*
    private final String PATH = "C:\\Users\\dinar\\Documents\\1-All Projects\\" +
            "#Java\\JavaLab\\JavaLab\\Annotations SOURCE\\target\\classes\\";
*/