//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.utils;

public class TimerSpammer
{
    private long lastTime;
    
    public long getCurrentTime() {
        return System.nanoTime() / 1000000L;
    }
    
    public TimerSpammer hasTimeElapsed(final long n) {
        if (this.getDifference() >= n) {
            return this;
        }
        return null;
    }
    
    public void reset() {
        this.lastTime = this.getCurrentTime();
    }
    
    public boolean hasReached(final long n) {
        return this.getDifference() >= n;
    }
    
    public long getLastTime() {
        return this.lastTime;
    }
    
    public TimerSpammer() {
        this.reset();
    }
    
    public long getDifference() {
        return this.getCurrentTime() - this.lastTime;
    }
}
