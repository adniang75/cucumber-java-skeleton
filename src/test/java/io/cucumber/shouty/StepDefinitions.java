package io.cucumber.shouty;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private Person lucy;
    private Person sean;
    private String messageFromSean;

    @Given("Lucy is located {int} meters from Sean")
    public void lucy_is_located_meters_from_sean(Integer distance) {
        // Write code here that turns the phrase above into concrete actions
        lucy = new Person();
        sean = new Person();
        lucy.moveTo(distance);
    }

    @When("Sean shouts {string}")
    public void sean_shouts(String message) {
        // Write code here that turns the phrase above into concrete actions
        sean.shout(message);
        messageFromSean = message;
    }

    @Then("Lucy hears Sean's message")
    public void lucy_hears_sean_s_message() {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(asList(messageFromSean), lucy.getMessagesHeard());
    }

}
