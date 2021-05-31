package br.com.softbank.relatorio.reflection;

public class Reflection {

	public Class<?> makeClass(String fullQualifiedName) throws Exception {
		return Class.forName(fullQualifiedName);
	}
	
	public Object invokeMethod(Object instance, String name) throws Exception {
		return instance.getClass().getDeclaredMethod(name).invoke(instance);
	}
}
