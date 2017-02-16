({
    initialize : function(component, event, helper) {
        var obj = { "name" : "",
                   	"dbName" : ""};
        component.set("v.entity", obj);
	},    
	handleAdd : function(component, event, helper) {		
        component.set("v.showForm", true);        
	},
    handleCancel : function(component, event, helper) {
        component.set("v.showForm", false);
        //clear form
        component.set("v.entity", {	"name" : "",
                  	"dbName" : ""});
        var showFieldForm = component.getEvent("showFieldForm");
        showFieldForm.setParams({"show" : false});
        showFieldForm.fire();
    },
    handleCreate : function(component, event, helper) {      
       var entity = component.get("v.entity");
       component.getEvent("createEntity").fire();
    },
    handleAddFields : function(component, event, helper) {
        //fire event to display add field form and field list
        var showFieldForm = component.getEvent("showFieldForm");
        showFieldForm.setParams({"show" : true});
        showFieldForm.fire();
    }
})