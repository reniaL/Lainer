package demo.cloning;

import com.rits.cloning.Cloner;


public class LainerCloner extends Cloner {
    
    public void setDontCloneInstanceOf(final Class<?>... cs) {
        super.dontCloneInstanceOf(cs);
    }
    
}
