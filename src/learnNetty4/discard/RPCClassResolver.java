package learnNetty4.discard;

import io.netty.handler.codec.serialization.ClassResolver;

public class RPCClassResolver implements ClassResolver {
    @Override
    public Class<?> resolve(String className) throws ClassNotFoundException {
        return null;
    }

    public static final RPCClassResolver instance = new RPCClassResolver();
}
