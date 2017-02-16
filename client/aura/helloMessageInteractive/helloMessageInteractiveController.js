({
	handleClick : function(component, event, helper) {
		var message = event.getSource().get("v.label");
        component.set("v.message", message);
	}
})