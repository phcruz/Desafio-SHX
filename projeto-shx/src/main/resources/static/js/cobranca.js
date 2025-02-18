$('#confirmacaoExclusaoModal').on(
		'show.bs.modal',
		function(event) {
			var button = $(event.relatedTarget);

			var codigoTitulo = button.data('codigo');
			var descricaoTitulo = button.data('descricao');

			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if (!action.endsWith('/')) {
				action += '/';
			}
			form.attr('action', action + codigoTitulo);

			$("#btnExcluir").attr("href", action + "delete/" + codigoTitulo);

			modal.find('.modal-body span').html(
					'Tem certeza que deseja excluir o cliente <strong>'
							+ descricaoTitulo + '</strong>?');
		});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({
		decimal : ',',
		thousands : '.',
		allowZero : true
	});
});
