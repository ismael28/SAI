/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contador;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
/**
 *
 * @author Ismael Sanchez
 */
public class agenteCont extends Agent{
    comportamientoCont comp = new comportamientoCont();
    protected void setup()
    {
        this.addBehaviour(comp);
    }
}

class comportamientoCont extends Behaviour{
    int contador = 1;
    public void action()
    {
        System.out.println(contador);
        contador++;
    }
    public boolean done()
    {
        return contador > 100;
    }
}
