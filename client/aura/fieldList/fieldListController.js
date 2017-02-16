({
	initialize : function(component, event, helper) {
		component.set("v.newFields", []);
        component.set("v.newFieldd", {});
	},
    addField : function(component, event, helper) {        
        var fields = component.get("v.newFields");
        var newFieldProxy = event.getParams("field");
        var newField = newFieldProxy.field;       
        fields.push(Object.assign({}, newField));
        component.set("v.newFields", fields);
    },
    handleRemove : function(component, event, helper) {        
        var fieldToRemove = event.getParam("field");
        var fields = component.get("v.newFields");
        fields = fields.filter(function(value) {
            return !Object.is(value, fieldToRemove)
        });
        component.set("v.newFields", fields);
    }
})