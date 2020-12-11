package agents;

import jade.core.Agent;
import behaviours.RequestPerformer;
import gui.BookBuyerGui;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookBuyerAgent extends Agent {
  public String bookTitle;
  private BookBuyerGui gui;
  public String titulo;
  private AID[] sellerAgents;
  private int ticker_timer = 10000;
  private BookBuyerAgent this_agent = this;
  
  protected void setup() {
     
    gui = new BookBuyerGui(this);
    gui.show();
      try {
          Thread.sleep(10000);
          
      } catch (InterruptedException ex) {
          Logger.getLogger(BookBuyerAgent.class.getName()).log(Level.SEVERE, null, ex);
      }
      System.out.println("Agente comprador " + getAID().getName() + " esta listo");
     //Object[] args = getArguments();
    titulo=gui.tt;
    gui.tfLibro.setText("");
    if(titulo != null && titulo.length() > 0) {
      bookTitle = titulo;
      System.out.println("Libro: " + bookTitle);
      
      addBehaviour(new TickerBehaviour(this, ticker_timer) {
        protected void onTick() {
          System.out.println("Intentando comprar " + bookTitle);
          
          DFAgentDescription template = new DFAgentDescription();
          ServiceDescription sd = new ServiceDescription();
          sd.setType("Venta de libros");
          template.addServices(sd);
          
          try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            System.out.println("Encontré los siguientes agentes vendedores:");
            sellerAgents = new AID[result.length];
            for(int i = 0; i < result.length; i++) {
              sellerAgents[i] = result[i].getName();
              System.out.println(sellerAgents[i].getName());
            }
            
          }catch(FIPAException fe) {
            fe.printStackTrace();
          }
          
          myAgent.addBehaviour(new RequestPerformer(this_agent));
        }
      });
    } else {
      System.out.println("Sin título de libro de destino especificado");
      doDelete();
    }
  }
 
  protected void takeDown() {
    System.out.println("Agente Comprador " + getAID().getName() + " terminado");
  }
  
  public AID[] getSellerAgents() {
    return sellerAgents;
  }
  
  public String getBookTitle() {
    return bookTitle;
  }
}
