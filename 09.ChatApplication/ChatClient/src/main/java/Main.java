import com.beust.jcommander.JCommander;
import net.Client;

/**
 * Main
 * created: 10-12-2020 - 0:46
 * project: 09.ChatApplication
 *
 * @author dinar
 * @version v0.1
 */
public class Main {

    public static void main(String[] argv) {
        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        String ipAddress = args.serverIp;
        int port = args.serverPort;

        new Thread(new Client(ipAddress, port)).start();
    }

}
