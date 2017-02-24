({
    initialize : function(component, event, helper) {        		
        helper.callGetAll(component, function() {}, helper.createToast );
	},
    refreshEntities : function(component, event, helper) {        
        var toggleSpinner = function(component) {
          $A.util.toggleClass(component.find("spinner"), "slds-hide");  
        }               
        helper.callGetAll(component, toggleSpinner, helper.createToast);
	},    
	toggleFieldForm : function(component, event, helper) {
		component.set("v.showFieldForm", event.getParam("show"));        
	},
    createEntity : function(component, event, helper) {
        var entityForm = component.find("entityForm");
      	var toggleSpinner = function(component) {
                $A.util.toggleClass(component.find("spinner"), "slds-hide"); 
            }
        var prom = new Promise(function(resolve, reject) {            
        	var entity = entityForm.get("v.entity");        
            if (component.get("v.showFieldForm")) {
                var fieldList = component.find("fieldList");
                var fields = fieldList.get("v.newFields");
                entity["fields"] = fields;    
            }
            toggleSpinner(component);
        	helper.persist(component, entity, resolve, reject);            
        });        
        prom.then($A.getCallback(function(id) {
            helper.createToast(component, "success", "Entity successfuly created. ID : " + id);           
            toggleSpinner(component);
            helper.callGetAll(component, toggleSpinner, helper.createToast);
            entityForm.set("v.entity", {
            	"name" : "",
            	"dbName" : ""
            });
            component.set("v.showFieldForm", false);
        }))        
        .catch($A.getCallback(function(errors) {
            toggleSpinner(component);
            console.log(errors[0]);
            helper.createToast(component, "error", "something wrong happened");
        }));         
      
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