package org.neobeans.jabracadabra;
 
import java.lang.instrument.Instrumentation;
 
public class RenameAgent {
    public static void premain(final String agentArgs,
                               final Instrumentation inst) {
	inst.addTransformer(new Transformer());
    }
 
    public static void agentmain(final String agentArgs,
                                 final Instrumentation inst) {
	inst.addTransformer(new Transformer());
    }
}
