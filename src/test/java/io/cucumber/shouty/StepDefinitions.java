package io.cucumber.shouty;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    private static final int DEFAULT_RANGE = 100;
    private String messageFromSean;
    private Network network = new Network( DEFAULT_RANGE );
    private Map<String, Person> people;

    @Before
    public void createNetwork () {
        people = new HashMap<>();
    }

    @Given( "the range is {int}" )
    public void the_range_is ( int range ) {
        network = new Network( range );
    }

    @Given( "a person named {word}" )
    public void a_person_named ( String name ) {
        people.put( name, new Person( network, 0 ) );
    }

    @Given( "a person named {word} is located at {int}" )
    public void a_person_named_is_located ( String name, int location ) {
        people.put( name, new Person( network, location ) );
    }

    @Given( "people are located at" )
    public void people_are_located_at ( io.cucumber.datatable.DataTable dataTable ) {
        System.out.println( dataTable.asMaps() );
    }


    @When( "Sean shouts" )
    public void sean_shouts () {
        people.get( "Sean" ).shout( "Hello, world" );
    }

    @When( "Sean shouts {string}" )
    public void sean_shouts_message ( String message ) {
        people.get( "Sean" ).shout( message );
        messageFromSean = message;
    }

    @Then( "Lucy should hear Sean's message" )
    public void lucy_hears_Sean_s_message () {
        assertEquals( Collections.singletonList( messageFromSean ), people.get( "Lucy" ).getMessagesHeard() );
    }

    @Then( "Lucy should hear a shout" )
    public void lucy_should_hear_a_shout () {
        assertEquals( 1, people.get( "Lucy" ).getMessagesHeard().size() );
    }

    @Then( "Larry should not hear a shout" )
    public void larry_should_not_hear_a_shout () {
        assertEquals( 0, people.get( "Larry" ).getMessagesHeard().size() );
    }
}