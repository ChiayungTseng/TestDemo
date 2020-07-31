package learnNetty4.discard;

public class RequestDealer {

    public String  threadId;

    final public Object waiter;

    public String responseValue;

    public RequestDealer(String threadId, Object waiter, String responseValue) {
        this.threadId = threadId;
        this.waiter = waiter;
        this.responseValue = responseValue;
    }
}
