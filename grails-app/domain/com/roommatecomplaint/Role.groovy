package com.roommatecomplaint

import org.bson.types.ObjectId

class Role {
	static mapWith = 'mongo'

	ObjectId id
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
