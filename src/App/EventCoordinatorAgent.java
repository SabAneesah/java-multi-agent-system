package App;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class EventCoordinatorAgent extends jade.core.Agent {

    @Override
    protected void setup() {
        System.out.println("EventCoordinatorAgent is ready.");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    System.out.println("EventCoordinatorAgent received: " + message.getContent());
                } else {
                    block();
                }
            }
        });
    }
}
