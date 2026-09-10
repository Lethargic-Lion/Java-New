package designPatterns.creational.prototype;

// Prototype class representing a network connection
// This is an example using shallow cloning
class NetworkConnection implements Cloneable {
    private String ipAddress;
    private int port;
    private String protocol;

    public NetworkConnection(String ipAddress, int port, String protocol) throws InterruptedException {
        this.ipAddress = ipAddress;
        this.port = port;
        this.protocol = protocol;
        Thread.sleep(5000);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "NetworkConnection{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                '}';
    }
}

// Class depicting Deeper cloning (if needed in future)
class DeepNetworkConnection implements Cloneable {
    private String ipAddress;
    private int port;
    private String protocol;
    private ConnectionSettings settings;

    public DeepNetworkConnection(String ipAddress, int port, String protocol, ConnectionSettings settings) throws InterruptedException {
        this.ipAddress = ipAddress;
        this.port = port;
        this.protocol = protocol;
        this.settings = settings;
        Thread.sleep(5000);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public ConnectionSettings getSettings() {
        return settings;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepNetworkConnection cloned = (DeepNetworkConnection) super.clone();
        cloned.settings = (ConnectionSettings) settings.clone();
        return cloned;
    }

    @Override
    public String toString() {
        return "DeepNetworkConnection{" +
                "ipAddress='" + ipAddress + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                ", settings=" + settings +
                '}';
    }
}

// Class representing connection settings for deep cloning
class ConnectionSettings implements Cloneable {
    private boolean keepAlive;
    private int timeout;

    public ConnectionSettings(boolean keepAlive, int timeout) {
        this.keepAlive = keepAlive;
        this.timeout = timeout;
    }
    public boolean isKeepAlive() {
        return keepAlive;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    @Override
    public String toString() {
        return "ConnectionSettings{" +
                "keepAlive=" + keepAlive +
                ", timeout=" + timeout +
                '}';
    }
}


// Client code to demonstrate Prototype Design Pattern
public class PrototypeDesignPattern {
    static void main() {
        NetworkConnection originalConnection = null;
        try {
            originalConnection = new NetworkConnection("192.62.5.5", 8080, "TCP");
            System.out.println("Original: " + originalConnection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        NetworkConnection clonedConnection;
        try {
            clonedConnection = (NetworkConnection) originalConnection.clone();
            System.out.println("Cloned: " + clonedConnection);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }


        // Demonstrating Deep Cloning
        ConnectionSettings settings = new ConnectionSettings(true, 5000);
        DeepNetworkConnection originalDeepConnection = null;
        try {
            originalDeepConnection = new DeepNetworkConnection("192.40.3.3", 9090, "UDP", settings);
            System.out.println("Original Deep: " + originalDeepConnection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DeepNetworkConnection clonedDeepConnection = null;
        try {
            clonedDeepConnection = (DeepNetworkConnection) originalDeepConnection.clone();
            System.out.println("Cloned Deep: " + clonedDeepConnection);
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }

        // Modifying cloned deep connection settings to show independence
        try {
            clonedDeepConnection.getSettings().setTimeout(10000);
            System.out.println("After modifying cloned deep connection settings:");
            System.out.println("Original Deep: " + originalDeepConnection);
            System.out.println("Cloned Deep: " + clonedDeepConnection);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // When other objects are involved, deep cloning is necessary to ensure complete independence between the original and cloned objects.
        // We need to write custom clone methods to achieve deep cloning.
        // In this example, ConnectionSettings is a separate object, so we clone it separately in the DeepNetworkConnection's clone method.
        // Example: if some list was involved, we would need to clone each element of the list as well in the clone method.
    }
}
