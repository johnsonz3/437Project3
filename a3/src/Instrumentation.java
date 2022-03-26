import java.util.ArrayList;
import java.util.Stack;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
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
    public boolean dump()
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
                output+="STOPTIMING: " + instance.getComment() + ((instance.getTime()-time)/1000000) + "ms \n";
                previous = "stop";
            }

            else if (type.equals("comment")) {
                output += "COMMENT:" + instance.getComment() + "\n";
            }
        }
        //something something write to file tbh I don't remember this very well
        try {
            FileWriter myWriter = new FileWriter("output.txt");
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
