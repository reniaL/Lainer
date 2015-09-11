package domain;

import java.util.List;

public class House {
    
    private String name;
    
    private String title;
    
    private List<Integer> roomAreas;
    
    public House() {
    }
    
    public House(String name, String title) {
        setName(name);
        setTitle(title);
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<Integer> getRoomAreas() {
        return roomAreas;
    }
    
    public void setRoomAreas(List<Integer> roomAreas) {
        this.roomAreas = roomAreas;
    }
    
}
