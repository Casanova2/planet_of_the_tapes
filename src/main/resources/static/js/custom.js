function mostrar_plist_series(){
	console.log("Rubén el master de la programación");
	document.getElementById("series").submit();
}
function mostrar_plist_movies(){
	console.log("Rubén el master de la programación");
	document.getElementById("movies").submit();
}
function mostrar_plist_videogames(){
	console.log("Rubén el master de la programación");
	document.getElementById("videogames").submit();
}

/*
Load More
*/
var portfolioLoadMore = {

	pages: 0,
	currentPage: 1,
	$wrapper: $('#portfolioLoadMoreWrapper'),
	$btn: $('#portfolioLoadMore-custom'),
	$btnWrapper: $('#portfolioLoadMoreBtnWrapper'),
	$loader: $('#portfolioLoadMoreLoader-custom'),

	build: function() {

		var self = this

		self.pages = self.$wrapper.data('total-pages');

		if(self.pages <= 1) {

			self.$btnWrapper.remove();
			return;

		} else {

			self.$btn.on('click', function() {
				self.loadMore();
			});

			// Infinite Scroll
			if(self.$btn.hasClass('btn-portfolio-infinite-scroll')) {
				self.$btn.appear(function() {
					self.$btn.trigger('click');
				}, {
					data: undefined,
					one: false,
					accX: 0,
					accY: 0
				});
			}

		}

	},
	loadMore: function() {

		var self = this;

		self.$btn.hide();
		self.$loader.addClass('portfolio-load-more-loader-showing').show();

		// Ajax
		$.ajax({
			url: 'ajax/portfolio-ajax-load-more-custom.html',
			complete: function(data) {

				var $items = $(data.responseText);

				setTimeout(function() {

					self.$wrapper.append($items)

					self.$wrapper.isotope('appended', $items);

					self.currentPage++;

					if(self.currentPage < self.pages) {
						self.$btn.show().blur();
					} else {
						self.$btnWrapper.remove();
					}

					// Carousel
					$(function() {
						$('[data-plugin-carousel]:not(.manual), .owl-carousel:not(.manual)').each(function() {
							var $this = $(this),
								opts;

							var pluginOptions = theme.fn.getOptions($this.data('plugin-options'));
							if (pluginOptions)
								opts = pluginOptions;

							$this.themePluginCarousel(opts);
						});
					});

					self.$loader.removeClass('portfolio-load-more-loader-showing').hide();

				}, 1000);

			}
		});

	}

}

if($('#portfolioLoadMoreWrapper').get(0)) {
	portfolioLoadMore.build();
}