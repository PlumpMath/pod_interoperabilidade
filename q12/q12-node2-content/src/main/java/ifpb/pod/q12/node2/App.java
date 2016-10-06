package ifpb.pod.q12.node2;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void startServer(int port) throws Exception{
        //
        String webappDirLocation = "src/webapp";
        //
        WebAppContext root = new WebAppContext();
        root.setContextPath("/");
        root.setDescriptor(webappDirLocation+"/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);
        root.setParentLoaderPriority(true);
        //
        Server server = new Server(port);
        server.setHandler(root);
        server.start();
        server.join();
    }

    public static void main(String[] args) throws Exception {
        //
        Integer rmiPort = 1099;
        Integer webPort = 80;
        //
        String _port = System.getenv("PORT");
        if(_port != null && !_port.isEmpty()) {
            webPort = Integer.parseInt(_port);
        }

        startServer(webPort);
    }
}
