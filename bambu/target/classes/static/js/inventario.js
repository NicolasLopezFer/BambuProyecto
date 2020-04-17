/**
 * 
 */

$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(articulo, status){
            $('.entradaForm #costoUnitario').val(articulo.unit_cost)
            $('.entradaForm #nombre').val(articulo.name)
        });
        $('.entradaForm #entradaModal').modal();
	});
});

$(document).ready(function() {
	$('.sBtn').on('click', function(event) {
        event.preventDefault();
        
        var href = $(this).attr('href');

        $.get(href, function(articulo, status){
            $('.entradaForm #costoUnitario').val(articulo.unit_cost)
            $('.entradaForm #nombre').val(articulo.name)
        });
        $('.editForm #entradaModal').modal();
	});
});

