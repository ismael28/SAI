/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author Ismael Sanchez
 */
public class Agente extends Agent{
    Comportamiento comp = new Comportamiento();
    protected void setup()
    {
        this.addBehaviour(comp);
    }

  }

class Comportamiento extends Behaviour{
    public void action()
            {
             System.out.println("Hola yo, soy el agente --> " +myAgent.getAID()+myAgent.getName());
            }
            public boolean done()
            {
                return true;
            }
}

