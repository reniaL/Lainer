package demo.jse;

public class AnonymousClassDemo {
    
    public static void main(String[] args) {
        AnonymousClassDemo instance = new AnonymousClassDemo();
        Base base = instance.getBase();
        base.print();
        
        base = instance.getB();
        base.print();
    }
    
    private String str = "outer str";
    
    private Base b = new Base(10) {
        
        @Override
        public void print() {
            str = "outer str modified";
            System.out.println("Anonymous print() outside method, str=" + str);
        }
    };
    
    public Base getB() {
        return b;
    }
    
    public Base getBase() {
        return new Base(5) { // 匿名类，调用父类的构造函数
        
            @SuppressWarnings("unused")
            private String str = "inner str"; // 跟外部类成员变量同名
            
            {
                i = 10; // 可以在初始化块中进行必要的初始化
            }
            
            @Override
            public void print() {
                System.out.println("Anonymous print(), i = " + i + ", str = " + AnonymousClassDemo.this.str); // 访问同名的外部类成员
            }
        };
    }
    
}

abstract class Base {
    
    protected int i;
    
    public Base(int i) {
        System.out.println("Base Constructor, i = " + i);
    }
    
    public abstract void print();
}