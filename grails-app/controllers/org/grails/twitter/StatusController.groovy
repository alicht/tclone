package org.grails.twitter

import org.grails.twitter.auth.Person

class StatusController {

    def springSecurityervice
    // will be injected into controller before the code executes

    def index() { 
    	def msgs = currentUserTimeline()
    	[messages: msgs]
    }

    def follow = {
        def per = Person.get(params.id)
        if(per){
            def currentUser = lookupPerson()
            currentUser.addToFollowed(per)
            currentUser.save()
        }
        redirect action: 'index'
    }

    def updateStatus = {
    	def status = new Status(message: params.message)
    	status.author = lookupPerson()
    	status.save()
    	def messages = currentUserTimeline()
    	render template: 'messages', collection: messages, var: 'message'
    }

    private currentUserTimeline(){
    	def per = lookupPerson()
    	def messages = Status.withCriteria{
    		or {
                author{
    				eq 'username', springSecurityervice.principal.username
    			}
                if(per.followed){
                    inList 'author', per.followed
                 }
                }
       		maxResults 10
    		order 'dateCreated', 'desc'
    }
    messages
}

    private lookupPerson() {
    Person.get(springSecurityervice.principal.username)
	}
}
