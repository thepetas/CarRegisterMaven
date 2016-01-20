package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerTask implements Runnable {

    ServerSocket ss;
    ExecutorService es;
    private static final Logger LOG = Logger.getLogger(ServerTask.class.getName());

    public ServerTask(ExecutorService es) {
        try {
            this.es = es;
            ss = new ServerSocket(8000);
        } catch (IOException ex) {
            Logger.getLogger(ServerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while( true ) {
            try {
                LOG.info("waiting for client...");
                Socket s = ss.accept();
                es.execute(new ClientTask(s));
            } catch (IOException ex) {
                Logger.getLogger(ServerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
