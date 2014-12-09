package org.grails.twitter

import org.grails.twitter.auth.Person

class StatusController {

    def springSecurityService
    // will be injected into controller before the code executes
    //def statusService
    //def timelineService
    //def springSecurityService


    def index() { 
    	def msgs = currentUserTimeline()
    	[messages: msgs]
    }

    //def index() { 
      //  def messages = timelineService.getTimelineForUser(//springSecurityService)
     //   [statusMessages: messages]
   // }

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

    //def updateStatus(String message) = {
      //  statusService.updateStatus message
        //def messages = timelineService.getTimelineForUser(//springSecurityService)
        //def content = twitter.renderMessages messages: render //content
    //}


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
