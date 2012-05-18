package com.roommatecomplaint

import java.util.Date;

import org.bson.types.ObjectId;

class Case {
	static mapWith = 'mongo'
	
	//ObjectId id
	//Long caseNumber
	Date dateCreated
	Integer guiltyVotes
	Integer innocentVotes
	
	EmbeddedUser plaintiff
	String plaintiffCaption
	String plaintiffImgURL
	
	EmbeddedUser defendant
	String defendantCaption
	String defendantImgURL
	
	Set<Comment> comments
	
	static embedded = ['plaintiff', 'defendant', 'comments']

    static constraints = {
		//caseNumber blank: false, unique: true
		defendantCaption nullable: true
		defendantImgURL nullable: true
    }
}
