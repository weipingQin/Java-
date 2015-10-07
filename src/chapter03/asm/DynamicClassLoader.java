package chapter03.asm;

import java.net.URLClassLoader;

public class DynamicClassLoader extends URLClassLoader {

	public DynamicClassLoader(URLClassLoader parentClassLoader) {
		super(parentClassLoader.getURLs() , parentClassLoader);
	}

	public Class<?> defineClassByByteArray(String className , byte[]bytes) {
		return this.defineClass(className , bytes, 0, bytes.length);
	}
}
