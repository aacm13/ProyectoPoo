/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

/**
 *
 * @author aacm12
 */
public class Dato{
    private String user;
    private String pass;
    private int high;
    private int money;

    public Dato() {
    }

    public Dato(String user, String pass, int high, int money) {
        this.user = user;
        this.pass = pass;
        this.high = high;
        this.money = money;
    }
    public Dato(String user, int high) {
        this.user = user;
        this.high = high;

    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public int getHigh() {
        return high;
    }

    public int getMoney() {
        return money;
    }
    

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    @Override
    public String toString() {
        return this.user + "," + this.pass + "," + Integer.toString(this.high) + ","+ Integer.toString(this.money);
    }

    
    
}
