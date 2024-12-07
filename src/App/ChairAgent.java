package App;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ChairAgent extends jade.core.Agent {

    @Override
    protected void setup() {
        System.out.println("ChairAgent is ready.");

        // Add behavior to handle messages
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive();
                if (message != null) {
                    System.out.println("ChairAgent received: " + message.getContent());

                    if (message.getContent().equals("Submit paper for review")) {
                        // Forward paper submission to reviewers
                        ACLMessage reviewRequest = new ACLMessage(ACLMessage.REQUEST);
                        reviewRequest.addReceiver(new AID("ReviewerAgent1", AID.ISLOCALNAME));
                        reviewRequest.addReceiver(new AID("ReviewerAgent2", AID.ISLOCALNAME));
                        reviewRequest.setContent("Please review the submitted paper.");
                        send(reviewRequest);
                        System.out.println("ChairAgent: Forwarding paper to reviewers.");
                    } else if (message.getContent().contains("Review complete")) {
                        // Handle feedback from reviewers
                        System.out.println("ChairAgent received review feedback: " + message.getContent());

                        ACLMessage feedbackToAuthor = new ACLMessage(ACLMessage.INFORM);
                        feedbackToAuthor.addReceiver(new AID("AuthorAgent", AID.ISLOCALNAME));
                        feedbackToAuthor.setContent("Your paper has been reviewed. Feedback provided.");
                        send(feedbackToAuthor);
                        System.out.println("ChairAgent: Forwarding feedback to AuthorAgent.");
                    }
                } else {
                    block();
                }
            }
        });
    }
}
