package oy.tol.tra;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() {
   }

   /** 
    * Execute from the command line:  <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

private void run() {
   try {
      // 1. Create a queue for daily tasks
      dailyTaskQueue = new QueueImplementation<String>();

      // 2. Read the tasks for today
      readTasks(); // Implement readTasks method to populate the dailyTaskQueue

      // 3. Create a Java Timer object
      timer = new Timer();

      // 4. Schedule the timer at a fixed rate with a new TimerTask
      timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
              if (!dailyTaskQueue.isEmpty()) {
                  System.out.println(dailyTaskQueue.dequeue());
              } else {
                  timer.cancel();
              }
          }
      }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
  } catch (IOException e) {
      System.out.println("Something went wrong :( " + e.getLocalizedMessage());
  }
}

private void readTasks() throws IOException {
   String tasks;
   tasks = new String(getClass().getClassLoader().getResourceAsStream("DailyTasks.txt").readAllBytes());
   String[] allTasks = tasks.split("\\r?\\n");
   for (String task : allTasks) {
       dailyTaskQueue.enqueue(task);
   }
   
   System.out.println("Number of tasks in the queue: " + allTasks.length);
   }
}

