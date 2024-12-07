package App;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AuthorAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println("AuthorAgent is ready.");

        // Submit a paper for review
        ACLMessage submitPaper = new ACLMessage(ACLMessage.REQUEST);
        submitPaper.addReceiver(new jade.core.AID("ChairAgent", jade.core.AID.ISLOCALNAME));
        submitPaper.setContent("Submit paper for review");
        send(submitPaper);
        System.out.println("AuthorAgent: Submitted paper to ChairAgent.");

        // Add behavior to handle feedback
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    System.out.println("AuthorAgent received: " + message.getContent());
                } else {
                    block();
                }
            }
        });
    }
}
