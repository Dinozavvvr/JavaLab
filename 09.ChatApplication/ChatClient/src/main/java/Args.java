import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Args
 * created: 10-12-2020 - 1:39
 * project: 09.ChatApplication
 *
 * @author dinar
 * @version v0.1
 */
@Parameters(separators = "=")
public class Args {

    @Parameter(names = {"--server-ip"})
    public String serverIp;

    @Parameter(names = {"--server-port"})
    public int serverPort;

}

