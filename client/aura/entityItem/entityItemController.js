({
	initialize : function(component, event, helper) {       
	},
    handleEdit : function(component, event, helper) {
        var menuItem = event.detail.menuItem;
        switch (menuItem.get("v.value")) {
            case "editfields" : 
                helper.updateFields(component, event);
                break;
            case "delete" :
                helper.executeDelete(component, event);                
                break;
            default : 
                break;
        }    
    },    
    toggleEditNameInput : function(component, event, helper) {
        var editInput = component.find("editNameInput");
        $A.util.toggleClass(editInput, "toggle");
    }
})