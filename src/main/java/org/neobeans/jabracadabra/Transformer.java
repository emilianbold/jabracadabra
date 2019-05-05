package org.neobeans.jabracadabra;
 
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
 
public class Transformer implements ClassFileTransformer {
    @Override
    public byte[] transform(final ClassLoader loader,
                            final String className,
                            final Class<?> classBeingRedefined,
                            final ProtectionDomain protectionDomain,
                            final byte[] classfileBuffer) {
        System.out.println(className);
 
        return null;
    }
}
