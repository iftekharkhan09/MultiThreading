package com.src.threads;
public class SequencePrinter {
	public static final int limit = 20;
	  
    public static void main(String... args) {
        final Counter counterObj = new Counter(20);
        Runnable evenNoPrinter = new Runnable() {
  
            public void run() {
                int num = 0;
                while (true) {
                    if (num >= limit) {
                        break;
                    }
                    num = counterObj.printNextEven();
                }
            }
        };
  
        Runnable oddNoPrinter = new Runnable() {
  
            public void run() {
                int num = 0;
                while (true) {
                    if (num >= limit) {
                        break;
                    }
                    num = counterObj.printNextOdd();
                }
            }
        };
  
        new Thread(oddNoPrinter).start();
        new Thread(evenNoPrinter).start();
  
 
    }
}
  
class Counter {
  
    private int count = 0;
    private boolean even = true;
    private int upperLimit;
  
    Counter(int limit) {
        upperLimit = limit;
    }
  
    public synchronized int printNextOdd() {
        //Wait until odd is available.
        while (even) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        count++;
        if (count <= upperLimit) {
            printEven(count);
        }
        //Toggle status.
        even = true;
        //Notify even printer that status has changed.        
        notifyAll();
        return count;
  
    }
  
    public synchronized int printNextEven() {
        //Wait until even is available.
        while (!even) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        count++;
        if (count <= upperLimit) {
            printOdd(count);
        }
        //Toggle status.
        even = false;
        //Notify odd printer that status has changed.        
        notifyAll();
        return count;
    }
  
    public void printOdd(int num) {
        System.out.println("ODD # " + num);
    }
  
    public void printEven(int num) {
        System.out.println("EVEN # " + num);
    }

}
