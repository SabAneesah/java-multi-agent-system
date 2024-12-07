package App;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class MainContainer {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            ProfileImpl profile = new ProfileImpl();
            profile.setParameter(Profile.MAIN, "true");
            profile.setParameter(Profile.GUI, "true"); // Enables RMA for monitoring

            // Create main container
            ContainerController mainContainer = runtime.createMainContainer(profile);

            // Start the RMA for monitoring
            AgentController rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();

            // Start agents
            AgentController chairAgent = mainContainer.createNewAgent("ChairAgent", ChairAgent.class.getName(), null);
            chairAgent.start();

            AgentController reviewerAgent1 = mainContainer.createNewAgent("ReviewerAgent1", ReviewerAgent.class.getName(), null);
            reviewerAgent1.start();

            AgentController reviewerAgent2 = mainContainer.createNewAgent("ReviewerAgent2", ReviewerAgent.class.getName(), null);
            reviewerAgent2.start();

            AgentController authorAgent = mainContainer.createNewAgent("AuthorAgent", AuthorAgent.class.getName(), null);
            authorAgent.start();

            AgentController eventCoordinator = mainContainer.createNewAgent("EventCoordinatorAgent", EventCoordinatorAgent.class.getName(), null);
            eventCoordinator.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

