package models;

import lombok.Builder;
import lombok.Data;

/**
 * created: 09-12-2020 - 21:45
 * project: Annotations SOURCE
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
public class Input {

    private String type;

    private String placeholder;

    private String name;

}
