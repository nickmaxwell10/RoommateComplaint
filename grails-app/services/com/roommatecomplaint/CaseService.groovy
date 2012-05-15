package com.roommatecomplaint

class CaseService {
	static transactional = 'mongo'
	
	def save(Case _case) {
		if(!_case.id) {
			_case.caseNumber = nextCaseNumber()	
		}
		
		if(!_case.save()) {
			return false
		}
		true
	}

    def nextCaseNumber() {
		def criteria = Case.createCriteria()
		
		def maxCaseNumber = criteria.get {
			projections {
				max('caseNumber')
			}
		}
		
		return maxCaseNumber ? maxCaseNumber++ : 1
	}
}
