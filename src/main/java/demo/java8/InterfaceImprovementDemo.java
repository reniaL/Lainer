package demo.java8;

public class InterfaceImprovementDemo {
	
	public static void main(String[] args) {
		Person p = new Person() {
			
			@Override
			public String getName() {
				return "hello";
			}
		};
		
		System.out.println(p.getName());
		System.out.println(p.getAge());
		Person.out();
		System.out.println();

		Person p2 = new Person() {
			
			@Override
			public String getName() {
				return "world";
			}
			
			@Override
			public int getAge() {
				return 20;
			}
		};
		
		System.out.println(p2.getName());
		System.out.println(p2.getAge());
	}

}