package multithreading;

class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " is using pen " + this + " to write on paper " + paper);
        paper.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " has finished writing with pen " + this);
    }
}

class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " is using paper " + this + " to write with pen " + pen);
        pen.finishWriting();
    }

    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " has finished writing on paper " + this);
    }
}

class Task1 implements Runnable {
    private final Pen pen;
    private final Paper paper;

    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }

    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}

class Task2 implements Runnable {
    private final Paper paper;
    private final Pen pen;

    public Task2(Paper paper, Pen pen) {
        this.paper = paper;
        this.pen = pen;
    }

    @Override
    public void run() {
//        paper.writeWithPaperAndPen(pen);
        synchronized (pen) {
            paper.writeWithPaperAndPen(pen);
        }
    }
}

public class DeadlockExample {
    static void main() {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread thread1 = new Thread(new Task1 (pen, paper), "Thread-1");
        Thread thread2 = new Thread(new Task2 (paper, pen), "Thread-2");

        thread1.start();
        thread2.start();
    }
}
