package demo.java8;

@FunctionalInterface
public interface Person {
	
	String getName();
	
	default int getAge() {
		return 0;
	}
	
	static void out() {
		System.out.println("I'm a person");
	}

}
