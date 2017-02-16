({
	submitForm : function(component, event, helper) {
        if (helper.validateCampingItemForm(component)) {
        	helper.createItem(component);    
        }		
	}
})