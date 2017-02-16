({
    initialize : function(component, event, helper) {
        
        var map =  { "DECIMAL" : { "length" : false, "mantissa" : false },
                           "BOOLEAN" : { "length" : true, "mantissa" : true },
                           "STRING" : { "length" : false, "mantissa" : true },
                           "MONEY" : { "length" : false, "mantissa" : false }            
        };
        component.set("v.disabledFieldsMap", map);
        var fieldlength = component.find("fieldlength");
        fieldlength.set("v.disabled", map[component.get("v.field.type")]["length"]);
        var fieldmantissa = component.find("fieldmantissa");
        fieldmantissa.set("v.disabled", map[component.get("v.field.type")]["mantissa"]);
        var constraints = component.find("constraints");
        var field = component.get("v.field");
        constraints.forEach(function(cmp) {           
            cmp.set("v.disabled", true);                
            if( field.constraints != undefined ) {
                var temp = field.constraints.filter(function(constraint) {
                    return constraint.type === cmp.get("v.value"); 
                });                
                if(temp.length > 0) {
                    cmp.set("v.checked", true);                      
                }
            }   
        });
        
        var fieldTypesSelect = component.find("fieldTypes");
        fieldTypesSelect.set("v.value", component.get("v.field.type"));
    },
	toggleDetails : function(component, event, helper) {
		helper.toggleDetails(component);
	},
    handleEdit : function(component, event, helper) {
       var details = component.find("panelDetails");
       $A.util.toggleClass(details, "non-editable");
       var constraints = component.find("constraints");
        if(constraints !== undefined) {
            constraints.forEach(function(cmp) {
            cmp.set("v.disabled", !cmp.get("v.disabled"));
            });
        } 
       var button = component.find("detailsButton");
        if (button.get("v.iconName") === "utility:down") {
            $A.util.toggleClass(details, "hide-details");
            button.set("v.iconName", "utility:up");
        }
    },
    handleDelete : function(component, event, helper) {
        var event = component.getEvent("deleteFieldFromPanel");
        event.setParam("field", component.get("v.field"));
        event.fire();        
    },
    onFieldTypeChange : function(component, event, helper) {        
        var newtype = component.find("fieldTypes").get("v.value");		        
        var map =  component.get("v.disabledFieldsMap");
        var disable = map[newtype];        
       	var fieldlength = component.find("fieldlength");
        fieldlength.set("v.disabled", disable["length"]);
        fieldlength.set("v.value", disable["length"] ? null : fieldlength.get("v.value") );
        var fieldmantissa = component.find("fieldmantissa");
        fieldmantissa.set("v.disabled", disable["mantissa"]);
        fieldmantissa.set("v.value", disable["mantissa"] ? null : fieldmantissa.get("v.value"));
        helper.sendChangeEvent(component);
    },
    onConstraintChange : function(component, event, helper) {
        if (event._source.get("v.checked")) {
            helper.addConstraint(component, event);
        } else {
            helper.removeConstraint(component, event);
        }
        helper.sendChangeEvent(component);
    },    
    handleSave : function(component, event, helper) {
        helper.saveField(component);
    },
    sendChangeEvent : function(component, event, helper) {
        helper.sendChangeEvent(component);
    }
})