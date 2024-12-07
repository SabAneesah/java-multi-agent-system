package App;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReviewerAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " is ready.");

        // Add behavior to handle messages
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    System.out.println(getLocalName() + " received: " + message.getContent());

                    if (message.getContent().equals("Please review the submitted paper.")) {
                        // Simulate reviewing process
                        System.out.println(getLocalName() + ": Reviewing the paper...");

                        // Send feedback to ChairAgent
                        ACLMessage feedback = new ACLMessage(ACLMessage.INFORM);
                        feedback.addReceiver(new jade.core.AID("ChairAgent", jade.core.AID.ISLOCALNAME));
                        feedback.setContent("Review complete: Paper is well-written with minor revisions.");
                        send(feedback);
                    }
                } else {
                    block();
                }
            }
        });
    }
}
