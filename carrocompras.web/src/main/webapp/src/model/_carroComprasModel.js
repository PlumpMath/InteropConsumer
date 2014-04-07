define([], function() {
    App.Model._CarroComprasModel = Backbone.Model.extend({
        defaults: {
 
		 'name' : ''
 ,  
		 'comprasId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 if(name=='comprasId'){  
                 var value = App.Utils.getModelFromCache('clienteComponent',this.get('comprasId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._CarroComprasList = Backbone.Collection.extend({
        model: App.Model._CarroComprasModel,
        initialize: function() {
        }

    });
    return App.Model._CarroComprasModel;
});