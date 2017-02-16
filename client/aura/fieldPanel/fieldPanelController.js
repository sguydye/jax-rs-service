({
	handleClose : function(component, event, helper) {
		helper.destroyPanel(component);		
	},
    toggleFieldForm : function(component, event, helper) {
        helper.toggleFieldForm(component);
    },
    handleSaveAll : function(component, event, helper) {       
        helper.persistEntity(component, helper.destroyPanel);
    },
    addField : function(component, event, helper) {
        helper.addFieldToList(component, event);
        helper.toggleFieldForm(component);		        
    },
    deleteField : function(component, event, helper) {
		helper.deleteFieldFromList(component, event);
	},
    onChange : function(component, event, helper) {
        component.find("saveButton").set("v.variant", "brand");
    }
})