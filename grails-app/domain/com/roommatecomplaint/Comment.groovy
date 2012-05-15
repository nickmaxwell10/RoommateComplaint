package com.roommatecomplaint

import org.bson.types.ObjectId;

class Comment {
	static mapWith = 'mongo'
	
	String text
	Date dateCreated
	Integer points
	EmbeddedUser user
	Boolean isGuilty
	
	static embedded = ['user']
	
    static constraints = {
    }
}
