package processors;

import annotations.HtmlForm;
import annotations.HtmlInput;
import com.google.auto.service.AutoService;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.annotation.processing.*;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

/**
 * HtmlProcessor
 * created: 09-12-2020 - 17:52
 * project: Annotations SOURCE
 *
 * @author dinar
 * @version v0.1
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"annotations.HtmlForm"})
public class HtmlProcessor extends AbstractProcessor {

    private String path;

    private Messager messager;

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager = processingEnv.getMessager();
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element element : annotatedElements) {
            HtmlForm htmlForm = element.getAnnotation(HtmlForm.class);
            path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName() + ".html";

            /* хотелось бы использовать это */
            Path out = Paths.get(path);

            /* но пришлось использовать это потому что у меня пробелы в пути */
            String filePath = "C:\\Users\\dinar\\Documents\\1-All Projects\\" +
                    "#Java\\JavaLab\\JavaLab\\Annotations SOURCE\\target\\classes\\"
                    + element.getSimpleName() + ".html";
            File file = new File(filePath);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                StringBuilder outForm = new StringBuilder("<form method='" + htmlForm.method() +
                        "' action='" + htmlForm.action() + "'>\n");

                List<? extends Element> enclosedElements = element.getEnclosedElements();

                for (Element enElement : enclosedElements) {
                    ElementKind elementKind = enElement.getKind();

                    if (elementKind.isField()) {
                        enElement.getSimpleName();
                        List<? extends AnnotationMirror> mirrors = enElement.getAnnotationMirrors();

                        for (AnnotationMirror mirror : mirrors) {
                            DeclaredType declaredType = mirror.getAnnotationType();
                            Element typeElement = declaredType.asElement();

                            if (typeElement.getSimpleName().contentEquals("HtmlInput")) {
                                HtmlInput input = enElement.getAnnotation(HtmlInput.class);
                                outForm.append("\t<input type='")
                                        .append(input.type())
                                        .append("' name='")
                                        .append(input.name())
                                        .append("' placeholder='")
                                        .append(input.placeholder())
                                        .append("'>\n");
                            }
                        }
                    }
                }

                outForm.append("\n</form>");
                writer.write(outForm.toString());
                writer.flush();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
        return true;
    }
}
