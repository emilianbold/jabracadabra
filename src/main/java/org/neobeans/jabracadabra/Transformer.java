package org.neobeans.jabracadabra;
 
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.ClassMap;
import javassist.CtClass;
import javassist.LoaderClassPath;
 
public class Transformer implements ClassFileTransformer {

	Map<ClassLoader, ClassPool> pools = new HashMap<>();

    @Override
    public byte[] transform(final ClassLoader loader,
                            final String className,
                            final Class<?> classBeingRedefined,
                            final ProtectionDomain protectionDomain,
                            final byte[] classfileBuffer) {
 

	ClassPool pool = pools.get(loader);
	if (pool == null) {
		ClassPool p = new ClassPool();
//		p.insertClassPath(new LoaderClassPath(loader));

		pools.put(loader, p);
		pool = p;
	}

	CtClass clazz = pool.getOrNull(className);

	if (clazz!=null) {
		//class already processed?
		System.out.println("Class " + className + " already in pool, skipping");
		return null;
	}
	System.out.println("Class " + className + " seen first time, adding to pool");
	pool.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
 	clazz = pool.getOrNull(className);

	assert clazz != null;

	PrintMap importMap = new PrintMap();
	clazz.replaceClassName(importMap);

	if(!importMap.isEmpty()) {
		System.out.println("Class " + className + " imports " + importMap.size() + " javax. classes which have been remapped to jakarta");
	} else {
		System.out.println("Class " + className + " has no javax");
	}


        return null;
    }

	public static class PrintMap extends ClassMap {

		public String get(Object jvmClassName) {
     			String name = toJavaName((String)jvmClassName);
     			if (name.startsWith("javax.") && get(name)==null) {
         			String name2 = toJvmName("jakarta." + name.substring(5));
				put(name, name2);
				return name2;
     			} else {
         			return super.get(jvmClassName);
			}
   		}
	}
}
