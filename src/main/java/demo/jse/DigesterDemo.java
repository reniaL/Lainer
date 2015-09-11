package demo.jse;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.digester.Digester;

import domain.House;

public class DigesterDemo {
    
    private int count = 0;
    
    public static void main(String[] args) {
        DigesterDemo demo = new DigesterDemo();
        long start = System.currentTimeMillis();
        String path = "/home/f_add.xml";
        demo.testRead(path);
        System.out.println("time: " + (System.currentTimeMillis() - start));
    }
    
    public void testRead(String path) {
        Digester digester = new Digester();
        digester.push(this);
        digester.addObjectCreate("House/Sell", House.class);
        digester.addBeanPropertySetter("House/Sell/PropertyName", "name");
        digester.addBeanPropertySetter("House/Sell/Id", "title");
        digester.addSetNext("House/Sell", "addHouse");
        digester.addObjectCreate("House/Rentout", House.class);
        digester.addBeanPropertySetter("House/Rentout/PropertyName", "name");
        digester.addBeanPropertySetter("House/Rentout/Id", "title");
        digester.addSetNext("House/Rentout", "addHouse");
        try {
            InputStream isr = new FileInputStream(path);
            digester.parse(isr);
        } catch (Exception e) {
            System.err.println("error parsing~~");
            e.printStackTrace();
            return;
        }
        System.out.println();
        System.out.println("count:" + count);
    }
    
    public void addHouse(House h) {
        count++;
        if (count == 1) {
            System.out.println(h.getName() + " " + h.getTitle());
        }
        if (count % 500 == 0) {
            System.out.print(count + " ");
            if (count % 10000 == 0) {
                System.out.println(h.getName());
            }
        }
    }
    
}
