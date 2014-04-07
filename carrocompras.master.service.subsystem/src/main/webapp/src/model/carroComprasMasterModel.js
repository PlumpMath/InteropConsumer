define(['model/_carroComprasMasterModel'], function() { 
    App.Model.CarroComprasMasterModel = App.Model._CarroComprasMasterModel.extend({
		initialize: function() {
            this.on('invalid', function(model,error) {
                Backbone.trigger('carroCompras-master-model-error', error);
            });
        },
        validate: function(attrs, options){
        	var modelMaster = new App.Model.CarroComprasModel();
        	if(modelMaster.validate){
            	return modelMaster.validate(attrs.carroComprasEntity,options);
            }
        }
    });

    App.Model.CarroComprasMasterList = App.Model._CarroComprasMasterList.extend({
        model: App.Model.CarroComprasMasterModel
    });

    return  App.Model.CarroComprasMasterModel;

});