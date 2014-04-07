define(['model/_carroComprasModel'], function() {
    App.Model.CarroComprasModel = App.Model._CarroComprasModel.extend({

    });

    App.Model.CarroComprasList = App.Model._CarroComprasList.extend({
        model: App.Model.CarroComprasModel
    });

    return  App.Model.CarroComprasModel;

});