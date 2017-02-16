({
	toggleDetails : function(component) {
		var details = component.find("panelDetails");
        $A.util.toggleClass(details, "hide-details");
        var button = component.find("detailsButton");
        switch(button.get("v.iconName")) {
            case "utility:down" :
                button.set("v.iconName", "utility:up");
                break;
            case "utility:up" :
                button.set("v.iconName", "utility:down");
                break;                
        }
	},
    addConstraint : function(component, event) {
        var constraints = component.get("v.field.constraints");        
        constraints.push({
            "type" : event._source.get("v.value") 
        });
        component.set("v.field.constraints", constraints);
    },
    removeConstraint : function(component, event) {
        var field = component.get("v.field");
        field.constraints = field.constraints.filter(function(constraint) {
           return constraint.type !== event._source.get("v.value"); 
        }); 
        component.set("v.field", field); 
    },
    saveField : function(component) {
        //this is where we make the put/patch call
        alert("REQUEST SENT");
    },
    sendChangeEvent : function(component) {
        component.getEvent("itemChanged").fire();
    }
})