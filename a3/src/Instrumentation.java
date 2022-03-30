import java.util.ArrayList;
import java.util.Stack;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Instrumentation {
    boolean activated = false;
    ArrayList<timing> timings;
    public Instrumentation()
    {
        this.timings = new ArrayList<>();
    }
    public boolean activate(boolean onoff)
    {
        activated = onoff;
        return activated;
    }
    public String startTiming(String comment)
    {
        if (!activated) {
            return null;
        }
        timings.add(new timing(comment, "start"));
        return comment;
    }
    public String stopTiming(String comment)
    {
        if (!activated) {
            return null;
        }
        timings.add(new timing(comment, "stop"));
        return comment;

    }
    public String comment(String comment)
    {
        if(!activated) {
            return null;
        }
        timings.add(new timing(comment, "comment"));
        return comment;

    }
    public boolean dump(String filename)
    {
        if(!activated) {
            return false;
        }
        Stack<timing> stack = new Stack<>();
        String output="";
        String previous="";
        int tabs=0;
        long time;
        for(timing instance:timings) {
            String type = instance.getType();

            if (type.equals("start")) {
                stack.push(instance);
                if (previous.equals("start")) {
                    tabs++;
                }
                for (int i = 0; i < tabs; i++) {
                    output += "\t";
                }
                output += "STARTTIMING: " + instance.getComment() + "\n";
                previous = "start";
            }

            else if (type.equals("stop")) {
                time = stack.pop().getTime();
                if(previous.equals("stop"))
                {
                    tabs--;
                }
                for (int i=0; i<tabs; i++)
                {
                    output+="\t";
                }
                long stopTime = ((instance.getTime()-time)/1000000);
                output+="STOPTIMING: " + instance.getComment() + " " + stopTime + "ms \n";
                previous = "stop";
                if (instance.getComment().equals("main")) {
                    output += "TOTAL TIME: " + stopTime + "ms \n";
                }
            }

            else if (type.equals("comment")) 
            {
                for (int i=0; i<tabs; i++)
                {
                    output+="\t";
                }
                output += "COMMENT: " + instance.getComment() + "\n";
            }
        }

        try {
            if (filename==null)
            {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("ddyyMMhhmmss");
                filename = "instrumentation"+dateFormat.format(c.getTime());
            }
            FileWriter myWriter = new FileWriter(filename+".log");
            myWriter.write(output);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    return true;

    }
}
