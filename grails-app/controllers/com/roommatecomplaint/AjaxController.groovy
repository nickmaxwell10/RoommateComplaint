package com.roommatecomplaint

import grails.converters.JSON;

class AjaxController {
	
	def springSecurityService

    def index() { }
	
	def filterFriends(String term) {
		def user = springSecurityService.currentUser
		if(user) {
			def friendsURL = "https://graph.facebook.com/me/friends?access_token=" + user.accessToken
			def friendsJSON = new URL(friendsURL).getText()
			def friends = JSON.parse(friendsJSON)
			def friendData = friends.data
			def filteredFriends = friendData.findAll{it.name.toLowerCase().find(term.toLowerCase())}
			def filteredFriendsJSON = filteredFriends.collect{ [label:it.name, value:it.name, id:it.id] }
			render filteredFriendsJSON as JSON
		}
	}
}
