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
    public String getComment()
    {
        return comment;
    }
    public long getTime()
    {
        return time;
    }
    public String getType()
    {
        return type;
    }
}
