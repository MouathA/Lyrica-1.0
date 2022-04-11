//Deobfuscated By Mouath#2221 | ????#2221 D:\Game\private 2\False"!

package me.d7omz.lyrica.utils;

public class TimerCStealer
{
    private long lastMS;
    
    public void setLastMS(final long lastMS) {
        this.lastMS = lastMS;
    }
    
    public boolean isDelayComplete(final long n) {
        return System.currentTimeMillis() - this.lastMS >= n;
    }
    
    public boolean hasReached(final long n) {
        return this.getCurrentMS() - this.lastMS >= n;
    }
    
    public TimerCStealer() {
        this.lastMS = 0L;
    }
    
    public void setLastMS() {
        this.lastMS = System.currentTimeMillis();
    }
    
    public boolean hasReachedfloat(final float n) {
        return this.getCurrentMS() - this.lastMS >= n;
    }
    
    public int convertToMS(final int n) {
        return 1000 / n;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
}
