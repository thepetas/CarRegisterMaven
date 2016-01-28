package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.RegisterFacade;
import model.car.Car;
import protocol.Command;
import protocol.Logout;
import utils.RegException;

public class ClientTask implements Runnable {

    private static final Logger LOG = Logger.getLogger(ClientTask.class.getName());

    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private final Socket s;

    Class[] clss = {Car.class};

    public ClientTask(Socket s) throws IOException {
        this.s = s;
        ois = new ObjectInputStream(s.getInputStream());
        oos = new ObjectOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = this.ois;
                ObjectOutputStream oos = this.oos;
                Socket s = this.s) {
            for (;;) {
                Command comm = (Command) ois.readObject();
                LOG.info(comm.toString());
                if (comm instanceof Logout) {
                    break;
                }
                Object result;
                try {
                    result = comm.execute(RegisterFacade.getService());
                } catch (RegException ex) {
                    result = ex;
                }
                LOG.info(result.toString());
                oos.writeObject(result);
                oos.flush();
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientTask.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
