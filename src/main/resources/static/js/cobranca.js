$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	form.attr('action', '/titulos/' + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});

$(function(){
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	$('.js-atualizar-status').on('click', function(event){
		event.preventDefault();

		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		console.log(urlReceber);
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		response.done(function(e){
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-codigo-td=' + codigoTitulo + ']').html('<span class="label label-success">' + e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e){
			console.log(e);
			alert('erro ao receber cobrança');
		});
	});
});