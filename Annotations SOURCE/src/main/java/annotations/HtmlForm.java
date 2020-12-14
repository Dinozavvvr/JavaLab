package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HtmlForm
 * created: 09-12-2020 - 17:46
 * project: Annotations SOURCE
 *
 * @author dinar
 * @version v0.1
 */

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface HtmlForm {

    String method() default "get";

    String action() default "/";

}
