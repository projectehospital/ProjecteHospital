/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cat.boscdelacoma.model.business.entities;

/**
 *
 * @author Victo
 */
public class Torn {

    private String tipusTorn;
    private long id;

    public Torn(String tipusTorn, long id) {
        this.tipusTorn = tipusTorn;
        this.id = id;
    }

    public Torn() {
    }

    public String getTipusTorn() {
        return tipusTorn;
    }

    public void setTipusTorn(String tipusTorn) {
        this.tipusTorn = tipusTorn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
