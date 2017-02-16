({
    initSuccess : function(component) {  
      $A.util.addClass(component.find("toast-wrap"), "slds-theme--success");
      component.find("icon").set("v.iconName", "utility:notification");
      component.find("icon").set("v.alternativeText", "Success"); 
    },
    initWarning : function(component) {
      $A.util.addClass(component.find("toast-wrap"), "slds-theme--warning");     
    },
	initError : function(component) {
        $A.util.addClass(component.find("toast-wrap"), "slds-theme--error");
        component.find("icon").set("v.alternativeText", "Error"); 
	}
    
})