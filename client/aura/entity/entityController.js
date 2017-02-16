({
    initialize : function(component, event, helper) {
        var entities = helper.callServerMock();        
		component.set("v.entities", entities); 
	},
    refreshEntities : function(component, event, helper) {
        $A.util.toggleClass(component.find("spinner"), "slds-hide");
        var entities = helper.callServerMock();
        window.setTimeout(function() {
            $A.util.toggleClass(component.find("spinner"), "slds-hide");
            component.set("v.entities", entities); 
        }, 1000);
    },    
	toggleFieldForm : function(component, event, helper) {
		component.set("v.showFieldForm", event.getParam("show"));
	},
    createEntity : function(component, event, helper) {
        var entityForm = component.find("entityForm");
        var entity = entityForm.get("v.entity");        
        if (component.get("v.showFieldForm")) {
        	var fieldList = component.find("fieldList");
        	var fields = fieldList.get("v.newFields");
        	entity["fields"] = fields;    
        }        
        helper.persist(component, entity, helper.createToast);        
        entityForm.set("v.entity", {
            "name" : "",
            "dbName" : ""
        });
		component.set("v.showFieldForm", false);
    },
    showFieldPanel : function(component, event, helper) {        
        helper.createFieldPanel(component, event);             
    },
    handleDeleteEntity : function(component, event, helper) {		
        helper.createConfirmModal(component, event);
        component.set("v.deletedEntityId", event.getParam("id"));
    },
    handleDeleteCancel : function(component, event, helper) {
        helper.handleDialogCancel(component, event);
    },
    handleDeleteConfirm : function(component, event, helper) {      
        helper.handleDialogCancel(component, event);
        helper.sendDeleteRequest(component, helper.createToast);
    },
    handleDestroyPanel : function(component, event, helper) {
        helper.destroyFieldPanel(component);
    },
    handleEntityUpdated : function(component, event, helper) {
        //call refresh probably
        helper.createToast(component, "success", "Changes saved");        
    }
})