({
    persist : function(component, entity, resolve, reject) {    
        var postAction = component.get("c.post");        
        postAction.setParams({
            "entity" : JSON.stringify(entity) 
        });
        postAction.setCallback(this, function(response) {            
            if(response.getState() === 'SUCCESS') {				                
				resolve(response.getReturnValue());                
            } else if(response.getState() === 'ERROR') {                
                reject(response.getError());
            }           
        });
        $A.enqueueAction(postAction);
	},
    createConfirmModal : function(component, event) {
        $A.createComponent(
            "c:confirmModal",
            {
                "aura:id" : "modalWindow",
                "header" : "Warning!",
                "message" : "Are you sure you want to delete this entity? All corresponding data will be lost.",
                "cancelCallback" : component.getReference("c.handleDeleteCancel"),
                "confirmCallback" : component.getReference("c.handleDeleteConfirm")
            },
            function(newModal, status, errorMessage){                
                if (status === "SUCCESS") {                    
                    var body = component.get("v.body");                    
                    body.push(newModal);                    
                    component.set("v.body", body);                    
                }
                else if (status === "INCOMPLETE") {
                    console.log("No response from server or client is offline.")                    
                }
                else if (status === "ERROR") {
                    console.log("Error: " + errorMessage);
                }
            }            
        );
    },
    handleDialogCancel : function(component, event) {
        var body = component.get("v.body");
        if(body.length > 0) {
            body = body.filter(function(cmp) {
            	return cmp.getLocalId() !== "modalWindow"
            });
        } 
        component.set("v.body", body);
    },
    sendDeleteRequest : function(component, callback) {
        var cb = arguments[arguments.length - 1];
        $A.util.toggleClass(component.find("spinner"), "slds-hide");        
       	var deleteAction = component.get("c.doDelete");
        deleteAction.setParams({
            'id' : component.get("v.deletedEntityId") 
        });
        deleteAction.setCallback(this, function(response) {
            var state = response.getState();
            if (component.isValid() && state === "SUCCESS") {
            	component.set("v.entities",
                          component.get("v.entities")
                            .filter(function(value) {
                                return value.id !== component.get("v.deletedEntityId");
                            })
                         );
                if( (typeof cb === 'function') && (component.isValid()) ) {
                    callback(component, "success", "Entity successfully deleted. ID : " + component.get("v.deletedEntityId"));
                }
            }
            $A.util.toggleClass(component.find("spinner"), "slds-hide");
            
        });
        $A.enqueueAction(deleteAction);
    },
    createToast : function(component, type, message) {        
        $A.createComponent(
                "c:toast",
                {
                    "aura:id" : "toast",
                    "type" : type,
                    "message" : message                
                },
                function(newToast, status, errorMessage){                
                    if (status === "SUCCESS") {                    
                        var body = component.get("v.body");                    
                        body.push(newToast);                    
                        component.set("v.body", body);                    
                    }
                    else if (status === "INCOMPLETE") {
                        console.log("No response from server or client is offline.")                    
                    }
                    else if (status === "ERROR") {                        
                        console.log("Error: " + errorMessage);
                    }
                }            
            );
            
            setTimeout(function() {
                var body = component.get("v.body");
                if(body.length > 0) {                        
                        body = body.filter(function(cmp) {
                            return cmp.getLocalId() !== "toast"
                        });
                    }                    
                component.set("v.body", body); 
            }, 2000);
    },
    createFieldPanel : function(component, event) {
        var entity = {};
        Object.assign(entity, event.getParam("entity"));        
        $A.createComponent(
            "c:fieldPanel",
            {
                "aura:id" : "fieldPanel",
                "parentEntity" : entity,
                "display" : "true"
            },
            function(newComponent, status, errorMessage){                
                if (status === "SUCCESS") {                    
                    var body = component.get("v.body");
                    if(body.length > 0) {                        
                        body = body.filter(function(cmp) {
                            return cmp.getLocalId() !== "fieldPanel"
                        });
                    }
                    body.push(newComponent);                    
                    component.set("v.body", body);					                     
                }
                else if (status === "INCOMPLETE") {
                    console.log("No response from server or client is offline.")                    
                }
                else if (status === "ERROR") {
                    console.log("Error: " + errorMessage);
                }
            }            
        );
    },
    destroyFieldPanel : function(component) {
		var body = component.get("v.body");        
        if(body.length > 0) {
            body = body.filter(function(cmp) {
            	return cmp.getLocalId() !== "fieldPanel"
            });
        } 
        component.set("v.body", body);
    },
    callGetAll : function(component, spinner, toast) {
		spinner(component);
        var action = component.get("c.getAll");
        action.setCallback(this, function(response) {
            if(response.getState() === 'SUCCESS') {
           		var val = response.getReturnValue();
           		var entities = JSON.parse(response.getReturnValue());            
           		component.set("v.entities", entities);
            } else {
                toast(component, "warning", "Something went wrong. Couldn't retrieve the data");
            }
            spinner(component);
        });
		$A.enqueueAction(action);
    }
})