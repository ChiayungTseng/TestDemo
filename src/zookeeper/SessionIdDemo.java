package zookeeper;

public class SessionIdDemo {


    public static void main(String[] args) {

        long i= 9;
        System.out.println(Long.toBinaryString(i));
        System.out.println(Long.toBinaryString(i<<2));
//        System.out.println(initializeNextSession(1));
    }

    public static long initializeNextSession(long id) {
        long nextSid = 0;
        long millis = System.currentTimeMillis();
        System.out.println("millis                    :"+Long.toBinaryString(millis));
        System.out.println("millis << 24              :"+Long.toBinaryString(millis << 24));
        System.out.println("nextSid=(millis<< 24)>>> 8:  "+Long.toBinaryString((millis << 24) >>> 8));
        nextSid = (millis << 24) >>> 8;
        System.out.println("id <<56                   :"+Long.toBinaryString(id <<56));
        nextSid =  nextSid | (id <<56);
        System.out.println("nextSid= nextSid|(id <<56):"+Long.toBinaryString(nextSid));
        return nextSid;
    }
}
