public class timing {
    private String comment;
    private long time;
    private String type;
    // "start", "stop", and "comment"
    public timing(String comment, String type)
    {
        this.comment = comment;
        this.type = type;
        time = System.nanoTime();
    }
    private String getComment()
    {
        return comment;
    }
    private long getTime()
    {
        return time;
    }
    private String getType()
    {
        return type;
    }
}
