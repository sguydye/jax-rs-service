({
	handleAddField : function(component, event, helper) {
        component = component.getSuper();
        helper.setConstraints(component);                
        //send field creation event        
        var newFieldEvent = $A.get("e.c:addNewField");
        newFieldEvent.setParams({"field" : component.get("v.field")});
        newFieldEvent.fire();
	}
})