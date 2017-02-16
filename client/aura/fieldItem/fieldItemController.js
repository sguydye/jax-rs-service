({
	handleRemove : function(component, event, helper) {
		var removeEvent = component.getEvent("removeField");
        removeEvent.setParam("field", component.get("v.newField"));
        removeEvent.fire();
	}
})