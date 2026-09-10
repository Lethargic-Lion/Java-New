package designPatterns.creational.singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// Singleton class implementation
// Ensures only one instance of Samosa is created
// Lazy initialization is used here
// Problem: Not thread-safe
class Samosa implements Serializable, Cloneable {
    private static Samosa samosaInstance;

    private Samosa() {
        // private constructor to prevent instantiation
    }

    public static Samosa getInstance() {
        if (samosaInstance == null) {
            samosaInstance = new Samosa();
        }
        return samosaInstance;
    }

    public void display() {
        System.out.println("Samosa instance: " + this);
    }

    // Overriding clone method to prevent cloning of singleton instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
//        throw new CloneNotSupportedException("Cloning of this singleton is not allowed");
    }
}

// Eager initialization
// Problem: Instance is created even if it might not be used
class Jalebi {
    private static final Jalebi jalebiInstance = new Jalebi();

    private Jalebi() {
        // private constructor to prevent instantiation
    }

    public static Jalebi getInstance() {
        return jalebiInstance;
    }

    public void display() {
        System.out.println("Jalebi instance: " + this);
    }
}

// Thread-safe Singleton using synchronized method
// Problem: Synchronized method can lead to performance issues
// if called frequently
class VadaPav {
    private static VadaPav vadaPavInstance;

    private VadaPav() {
        // private constructor to prevent instantiation
    }
    public static synchronized VadaPav getInstance() {
        if (vadaPavInstance == null) {
            vadaPavInstance = new VadaPav();
        }
        return vadaPavInstance;
    }
    public void display() {
        System.out.println("VadaPav instance: " + this);
    }
}

// Thread-safe Singleton using double-checked locking
// Volatile keyword ensures visibility of changes to variables across threads
// More efficient than synchronized method
// Recommended approach for thread-safe Singleton implementation in multi-threaded environments
// Lazy initialization with double-checked locking
class PavBhaji {
    private static volatile PavBhaji pavBhajiInstance;
    private PavBhaji() {
        // private constructor to prevent instantiation
    }
    public static PavBhaji getInstance() {
        if (pavBhajiInstance == null) {
            synchronized (PavBhaji.class) {
                if (pavBhajiInstance == null) {
                    pavBhajiInstance = new PavBhaji();
                }
            }
        }
        return pavBhajiInstance;
    }
    public void display() {
        System.out.println("PavBhaji instance: " + this);
    }
}

// Avoiding breaking Singleton with Reflection API
// One way to prevent this is to throw an exception in the constructor
// if an instance already exists
// However, this is not foolproof and can still be bypassed
// A better approach is to use Enum for Singleton implementation
// which is inherently safe from reflection and serialization attacks
// Here, we demonstrate the vulnerability for educational purposes only
class AlooTikki {
    private static AlooTikki alooTikkiInstance;

    private AlooTikki() {
        if (alooTikkiInstance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static AlooTikki getInstance() {
        if (alooTikkiInstance == null) {
            synchronized (AlooTikki.class){
                if (alooTikkiInstance == null) {
                    alooTikkiInstance = new AlooTikki();
                }
            }
        }
        return alooTikkiInstance;
    }

    public void display() {
        System.out.println("AlooTikki instance: " + this);
    }
}

// Avoiding breaking Singleton with Enum
enum GulabJamun {
    INSTANCE;
    public void display() {
        System.out.println("GulabJamun instance: " + this);
    }
}

// Avoiding breaking Singleton with Serialization
// Implementing readResolve method to return the existing instance
// This prevents creating a new instance during deserialization
class Rasgulla implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Rasgulla rasgullaInstance = new Rasgulla();

    private Rasgulla() {
        // private constructor to prevent instantiation
    }

    public static Rasgulla getInstance() {
        return rasgullaInstance;
    }

    protected Object readResolve() {
        return rasgullaInstance;
    }

    public void display() {
        System.out.println("Rasgulla instance: " + this);
    }
}

// Best way to implement Singleton pattern when Serialization, Reflection, and Cloning attacks need to be prevented
// Using Enum is the most effective way to implement Singleton pattern in Java
enum DesignPatternSingleton {
    INSTANCE;
    public void display() {
        System.out.println("DesignPatternSingleton instance: " + this);
    }
}

public class Singleton {
    static void main() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException, CloneNotSupportedException {
        Samosa samosa1 = Samosa.getInstance();
        Samosa samosa2 = Samosa.getInstance();
        samosa1.display();
        samosa2.display();
        System.out.println("Samosa instances are the same: " + (samosa1 == samosa2));
        // Hashcode will be same for both instances

        Jalebi jalebi1 = Jalebi.getInstance();
        Jalebi jalebi2 = Jalebi.getInstance();
        jalebi1.display();
        jalebi2.display();
        System.out.println("Jalebi instances are the same: " + (jalebi1 == jalebi2));
        System.out.println("Hashcode of jalebi1: " + jalebi1.hashCode());
        System.out.println("Hashcode of jalebi2: " + jalebi2.hashCode());

        VadaPav vadaPav1 = VadaPav.getInstance();
        VadaPav vadaPav2 = VadaPav.getInstance();
        vadaPav1.display();
        vadaPav2.display();
        System.out.println("VadaPav instances are the same: " + (vadaPav1 == vadaPav2));

        PavBhaji pavBhaji1 = PavBhaji.getInstance();
        PavBhaji pavBhaji2 = PavBhaji.getInstance();
        pavBhaji1.display();
        pavBhaji2.display();
        System.out.println("PavBhaji instances are the same: " + (pavBhaji1 == pavBhaji2));

        // Breaking Singleton using Reflection API (for demonstration purposes)
        Constructor<Samosa> samosaConstructor = Samosa.class.getDeclaredConstructor();
        samosaConstructor.setAccessible(true);
        Samosa newSamosa = samosaConstructor.newInstance();
        System.out.println("New Samosa hashcode: " + newSamosa.hashCode());
        System.out.println("Samosa instance from getInstance hashcode: " + samosa1.hashCode());

        // Avoiding breaking Singleton with Exception in constructor
        try {
            AlooTikki alooTikki1 = AlooTikki.getInstance();
            Constructor<AlooTikki> alooTikkiConstructor = AlooTikki.class.getDeclaredConstructor();
            alooTikkiConstructor.setAccessible(true);
            AlooTikki newAlooTikki = alooTikkiConstructor.newInstance();
        } catch (Exception e) {
            System.out.println("Reflection attack prevented: " + e);
        }

        // Using Enum Singleton
        GulabJamun gulabJamun1 = GulabJamun.INSTANCE;
        GulabJamun gulabJamun2 = GulabJamun.INSTANCE;
        gulabJamun1.display();
        gulabJamun2.display();
        System.out.println("GulabJamun instances are the same: " + (gulabJamun1 == gulabJamun2));
        System.out.println("Hashcode of gulabJamun1: " + gulabJamun1.hashCode());
        System.out.println("Hashcode of gulabJamun2: " + gulabJamun2.hashCode());

        // Using Serialization-safe Singleton
        Rasgulla rasgulla1 = Rasgulla.getInstance();
        Rasgulla rasgulla2 = null;
        System.out.println("Rasgulla instance before serialization: " + rasgulla2);
        // Serialization and Deserialization process would go here
        rasgulla2 = Rasgulla.getInstance(); // Simulating deserialization
        rasgulla1.display();
        rasgulla2.display();
        System.out.println("Rasgulla instances are the same: " + (rasgulla1 == rasgulla2));
        System.out.println("Hashcode of rasgulla1: " + rasgulla1.hashCode());
        System.out.println("Hashcode of rasgulla2: " + rasgulla2.hashCode());

        // Breaking Serialization-safe Singleton (for demonstration purposes)
        Samosa samosa3 = Samosa.getInstance();
        System.out.println("Samosa3 hashcode: " + samosa3.hashCode());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("samosa.ser"));
        oos.writeObject(samosa3);
        oos.close();
        System.out.println("Serialized Samosa instance to samosa.ser");
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("samosa.ser"));
        Samosa deserializedSamosa = (Samosa) ois.readObject();
        ois.close();
        System.out.println("Deserialized Samosa hashcode: " + deserializedSamosa.hashCode());

        // Breaking the singleton pattern using cloning
        // Note: This requires Samosa to implement Cloneable and override clone() method
        Samosa samosa4 = Samosa.getInstance();
        System.out.println("Samosa 4 hashcode: " + samosa4.hashCode());
        Samosa clonedSamosa = (Samosa) samosa4.clone();
        System.out.println("Cloned Samosa hashcode: " + clonedSamosa.hashCode());

    }
}
