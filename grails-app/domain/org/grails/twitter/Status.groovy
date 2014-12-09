package org.grails.twitter

//import.org.grails.twitter.auth.Person

class Status {

	String message
	Person author
	Date dateCreated

    static constraints = {
    	message size: 2..141 
    }
}
