package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.car.Car;
import protocol.Command;
import protocol.Logout;
import utils.RegException;
import utils.Messages;

public class Connection {

    private static final Logger LOG = Logger.getLogger(Connection.class.getName());

    public static final Connection instance = new Connection();

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket s;
    private static Class[] clss = {Car.class};

    public void connect(InetAddress host, int port) throws IOException {
        s = new Socket(host, port);
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
    }
    
    public int getPort(){
        return s.getPort();
    }

    public void disconnect() throws RegException {
        if (!isConnected()) {
            return;
        }
        try (ObjectOutputStream oos = this.oos;
                ObjectInputStream ois = this.ois;
                Socket s = this.s) {
            send(new Logout());
            this.s = null;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public <T> T send(Command com) throws RegException {
        if (!isConnected()) {
            throw new RegException(Messages.Not_connected.createMess());
        }
        try {
            oos.writeObject(com);
            oos.flush();
            if (com instanceof Logout) {
                return null;
            }
            T result = (T) ois.readObject();
            if (result instanceof Exception) {
                throw (RegException) result;
            }
            return result;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }

    public boolean isConnected() {
        return s != null;
    }

}
