import java.util.Stack;

public class Instrumentation {
    boolean activated = false;
    boolean lastTimeIsStop = true;
    int tabs=0;
    String output="";
    Stack<timing> timings;
    public Instrumentation()
    {
        Stack<timing> timings = new Stack<timing>();
    }
    public boolean activate(boolean onoff)
    {
        activated = onoff;
        return activated;
    }
    public String startTiming(String comment)
    {
        if (activated) {
            timings.push(new timing(comment, "start"));
            return comment;
        }
        return null;
    }
    public String stopTiming(String comment)
    {
        if (activated) {
            timings.push(new timing(comment, "stop"));
            return comment;
        }
        return null;
    }
    public boolean comment(String comment)
    {
        if(activated) {
            timings.push(new timing(comment, "comment"));
            return true;
        }
        return false;
    }

}
