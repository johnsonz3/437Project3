public class main {
    public static void main(String[] args) {
        Instrumentation ins= new Instrumentation();
        ins.activate(true);
        ins.startTiming("main");
        ins.startTiming("loop");
        for (int i=0; i<5; i++)
        {
            doSomeStuff(ins);
        }

        ins.stopTiming("loop");
        ins.comment("this is an example of a comment!");
        ins.stopTiming("main");
        ins.dump("output");
    }

    public static void doSomeStuff(Instrumentation ins){
        ins.startTiming("doSomeStuff()");
        try
        {
            Thread.sleep(1);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
        ins.stopTiming("doSomeStuff()");
    }
}
