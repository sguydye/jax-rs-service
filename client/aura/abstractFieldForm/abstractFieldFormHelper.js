({
	setConstraints : function(component) {
		var constraints = [];
        var constraintsComponent = component.find("constraints");
        constraintsComponent.forEach(function(element){
            if(element.get("v.checked") === true){
                var constraint = {};
                constraint["type"] = element.get("v.name");
                constraints.push(constraint);
            }
        });
        
        var field = component.get("v.field");
        field.constraints = constraints;
        component.set("v.field", field);
	}
})