package com.roommatecomplaint

class CaseController {
	
	def springSecurityService

    def index(Long id)  {
		
		if(request.method == 'POST') {
			
		}
		
		def model = [:]
		model.user = springSecurityService.currentUser
		render(view:'case', model: model, params: [id:id])
	}
	
	def newCase = {
		Case newCase = new Case()
		
		if(request.method == 'POST') {
			//save case
			
			redirect(action:"index", id:newCase.caseNumber)
			return
		}
		
		def model = [:]
		model.user = springSecurityService.currentUser
		render(view:'newCase', model: model, params: null)
	}
}
