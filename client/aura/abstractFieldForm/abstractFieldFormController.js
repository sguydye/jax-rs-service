({
	initialize : function(component, event, helper){
        var obj = { "name" : "",
                   "type" : component.get("v.fieldTypes")[0],
                   "length" : null,
                   "mantissa" : null,
                   "constraints" : []
                   };
        component.set("v.field", obj);
		var map =  { "DECIMAL" : { "length" : false, "mantissa" : false },
                           "BOOLEAN" : { "length" : true, "mantissa" : true },
                           "STRING" : { "length" : false, "mantissa" : true },
                           "MONEY" : { "length" : false, "mantissa" : false }            
        };
        component.set("v.disabledFieldsMap", map);
    },    
    onFieldTypeChange : function(component, event, helper) {
        var field = component.get("v.field");
        field.type = component.find("fieldTypes").get("v.value");
        component.set("v.field", field);
                
        var map =  component.get("v.disabledFieldsMap");
        var disable = map[field.type];
       	var fieldlength = component.find("fieldlength");
        fieldlength.set("v.disabled", disable["length"]);
        fieldlength.set("v.value", disable["length"] ? null : fieldlength.get("v.value") );
        var fieldmantissa = component.find("fieldmantissa");
        fieldmantissa.set("v.disabled", disable["mantissa"]);
		fieldmantissa.set("v.value", disable["mantissa"] ? null : fieldmantissa.get("v.value"));        
    }
})