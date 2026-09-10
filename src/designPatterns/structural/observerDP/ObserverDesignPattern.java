package designPatterns.structural.observerDP;

import java.util.ArrayList;
import java.util.List;

interface Subject {
    void subscribeObserver(Observer o);
    void unsubscribeObserver(Observer o);
    void notifyObservers(String event);
}

interface Observer {
    void notified(String event);
}

class Subscriber implements Observer {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void notified(String event) {
        System.out.println("Subscriber " + this.name + " notified of event: " + event);
    }
}

class YoutubeChannel implements Subject{
    List<Observer> subscribers = new ArrayList<>();

    @Override
    public void subscribeObserver(Observer o) {
        this.subscribers.add(o);
    }

    @Override
    public void unsubscribeObserver(Observer o) {
        this.subscribers.remove(o);
    }

    @Override
    public void notifyObservers(String event) {
        for(Observer o: this.subscribers){
            o.notified(event);
        }
    }
}

public class ObserverDesignPattern{
    static void main() {
        YoutubeChannel techChannel = new YoutubeChannel();
        Subscriber alice = new Subscriber("Alice");
        Subscriber bob = new Subscriber("Bob");
        techChannel.subscribeObserver(alice);
        techChannel.subscribeObserver(bob);
        techChannel.notifyObservers("Learn Observer Pattern in Java");
        techChannel.unsubscribeObserver(bob);
        techChannel.notifyObservers("New Video Released on Design Patterns");
    }
}


class A {
    A() {
        System.out.println("A");
    }
}
