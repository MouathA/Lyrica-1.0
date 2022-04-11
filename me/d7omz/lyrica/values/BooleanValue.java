package me.d7omz.lyrica.values;

public class BooleanValue
{
    private boolean value;
    private String name;
    
    public String getName() {
        return this.name;
    }
    
    public boolean getState() {
        return this.value;
    }
    
    public void setState(final boolean value) {
        if (value == this.value) {
            return;
        }
        this.value = value;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void toggle() {
        this.value = !this.value;
    }
    
    public BooleanValue(final String name, final boolean value) {
        this.name = name;
        this.value = value;
    }
}
