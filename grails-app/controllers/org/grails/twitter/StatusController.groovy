package org.grails.twitter

import org.grails.twitter.auth.Person

class StatusController {

    def springSecurityervice

    def index() { }

    def updateStatus = {
    	def status = new Status(message: params.message)
    	status.author = lookupPerson()
    	status.save()
    	def messages = currentUserTimeline()
    	render template: 'messages', collection: messages, var: 'message'
    }
    private lookupPerson() {
    Person.get(springSecurityervice.principal.id)
	}
}
