({
    callServerMock : function() {
        
        /*
        var xhr = new XMLHttpRequest();
        console.log(xhr);
   		xhr.open('GET', 'https://54.172.220.99:8443/jax-rs-service-0.1.0/services/entity/',  true); 
   		xhr.send(null);
        */
        
        
        var entities = [{ "id" : 1,
            			  "name" : "Taxes",
                          "dbName" : "DB1",
                          "createdDate" : "10/01/2017",
                          "lastModifiedDate" : "21/01/2017",
                          "fields" : [
                              {
                                  "name" : "Tax Type",
                                  "type" : "STRING",
                                  "length" : 60,
                                  "constraints" : [
                                      {
                                          "type" : "UNIQUE"
                                      }, {
                                          "type" : "NOTNULL"
                                      }
                                  ]
                              },
                              {
                                  "name" : "Amout",
                                  "type" : "MONEY",
                                  "length" : 30,
                                  "mantissa" : 2,
                                  "constraints" : [
                                      {
                                          "type" : "NOTNULL"
                                      }
                                  ]
                              },
                              {
                                  "name" : "Paid",
                                  "type" : "BOOLEAN"                                  
                              }
                          ]
                        },{ "id" : 2,
                            "name" : "Expenses",
                            "dbName" : "DB1",
                            "createdDate" : "10/01/2017",
                            "lastModifiedDate" : "21/01/2017"            
                        },{ "id" : 3, 
            				"name" : "Profits",
            				"dbName" : "DB2",
            				"createdDate" : "10/01/2017",
            				"lastModifiedDate" : "21/01/2017"
            
        }];
        
        entities.forEach(function(entity) {
            if(entity.fields === undefined) 
                entity.fields = [];
            entity.fields.forEach(function(field) {
                if(field.constraints === undefined){
                    field.constraints = [];
                } 
            });
        });
        return entities;
	},
	persist : function(component, entity, callback) {
        //persist entity        
        $A.util.toggleClass(component.find("spinner"), "slds-hide");
        console.log("Sending post request for new entity");
		var cb = arguments[arguments.length - 1];		       
        window.setTimeout($A.getCallback(function() {
            $A.util.toggleClass(component.find("spinner"), "slds-hide");
            console.log("Request success. Entity created:");
            console.log(entity);            
        	var entities = component.get("v.entities");
        	entities.push(entity);
        	component.set("v.entities", entities);            
            if( (typeof cb === 'function') && (component.isValid()) ) {
                callback(component, "success", "Entity successfuly created : " + entity.name);    
            }			            
        }), 2000);  	
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
        console.log("Sending delete request");
        //on success        
        setTimeout($A.getCallback(function() {            
            //alternative - send get request to refresh entitites
            component.set("v.entities",
                          component.get("v.entities")
                            .filter(function(value) {
                                return value.id !== component.get("v.deletedEntityId");
                            })
                         );                
            $A.util.toggleClass(component.find("spinner"), "slds-hide");
            if( (typeof cb === 'function') && (component.isValid()) ) {
                callback(component, "success", "Entity successfully deleted. ID : " + component.get("v.deletedEntityId"));
            }
        }), 2000);

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
    }
})