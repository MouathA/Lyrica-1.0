//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.utils;

public class TimerTriggerBot
{
    private long lastMS;
    
    public long getTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean delay(final float n) {
        return this.getTime() - this.lastMS >= n;
    }
    
    public boolean hasReached(final double n) {
        return this.getCurrentMS() - this.lastMS >= n;
    }
    
    private long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
}
