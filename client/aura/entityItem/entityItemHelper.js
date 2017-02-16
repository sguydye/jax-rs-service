({
	executeDelete : function(component, event) {
    var deleteEvent = component.getEvent("deleteEntity");
        deleteEvent.setParam("id", component.get("v.entity.id"));
        deleteEvent.fire();     
	},
    updateFields : function(component, event) {
        var updateEvent = component.getEvent("updateFields");
        updateEvent.setParam("entity", component.get("v.entity"));
        updateEvent.fire();        
    }    
})