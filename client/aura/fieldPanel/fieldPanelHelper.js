({
	persistEntity : function(component, callback) {
        var cb = arguments[arguments.length - 1];	
        console.log("Sending put request on entity:");
        console.log(component.get("v.parentEntity"));
        $A.util.toggleClass(component.find("spinner"), "slds-hide");
        setTimeout(function() {
            $A.util.toggleClass(component.find("spinner"), "slds-hide");
			component.getEvent("entityUpdated").fire();            
            if(typeof cb === 'function') {
            	callback(component);                
            }        	    
        }, 2000);
	},
    destroyPanel : function(component) {
        var event = component.getEvent("panelDestroyed");
        event.fire();
    },
    toggleFieldForm : function(component) {
        $A.util.toggleClass(component.find("formWrapper"), "slds-hide");
        $A.util.toggleClass(component.find("addButton"), "slds-hide");
        $A.util.toggleClass(component.find("cancelAdd"), "slds-hide");
    },
    addFieldToList : function(component, event) {
        var entity = component.get("v.parentEntity");
        var newFieldProxy = event.getParams("field");
        var newField = newFieldProxy.field;
        var fields = entity.fields;
        fields.push(Object.assign({}, newField));
        entity.fields = fields;        
        component.set("v.parentEntity", entity);
    },
    deleteFieldFromList : function(component, event) {
        var field = event.getParam("field");
        var parentEntity = component.get("v.parentEntity");        
        var fields = parentEntity.fields;
        fields = fields.filter(function(item) {
           return !Object.is(item, field) 
        });
        parentEntity.fields = fields;
        component.set("v.parentEntity", parentEntity);
    }
})