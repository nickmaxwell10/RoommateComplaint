package com.roommatecomplaint

import org.bson.types.ObjectId;

class EmbeddedUser {
	static mapWith = 'mongo'
	
	ObjectId id
	String uid //facebook id
	String name

    static constraints = {
		id nullable: true
		uid blank: false, unique: true
    }
}
