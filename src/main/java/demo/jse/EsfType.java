package demo.jse;

public enum EsfType {
    
    /*
     * 枚举常量的声明，会调用构造函数
     * 必须位于构造函数、字段和方法的前面
     */
    SALERENT("salerent", "s"), JOINRENT("joinrent", "j"), REQUEST("request", "r");
    
    private final String type;
    
    private final String name;
    
    EsfType(String name, String type) {
        this.type = type;
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
}
