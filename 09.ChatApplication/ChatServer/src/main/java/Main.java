import com.beust.jcommander.JCommander;
import net.Server;

/**
 * Main
 * created: 10-12-2020 - 0:49
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

        int port = args.port;

        new Thread(new Server(port)).start();
    }

}
