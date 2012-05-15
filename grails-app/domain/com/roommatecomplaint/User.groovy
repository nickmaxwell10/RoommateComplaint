package com.roommatecomplaint

import org.bson.types.ObjectId

class User {
	static mapWith = 'mongo'

	transient springSecurityService

	ObjectId id
	String uid //facebook id
	String accessToken
	String name
	Date dateCreated

    Set<Role> authorities

    static embedded = ['authorities']
    
    static constraints = {
        uid blank: false, unique: true
    }

    static mapping = {
        
    }
}
